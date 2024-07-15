package twilightforest.asm.transformers.beardifier;

import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.ITransformerVotingContext;
import cpw.mods.modlauncher.api.TargetType;
import cpw.mods.modlauncher.api.TransformerVoteResult;
import net.neoforged.coremod.api.ASMAPI;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import twilightforest.asm.ASMUtil;

import java.util.Set;

/**
 * {@link twilightforest.ASMHooks#gatherCustomTerrain}
 */
public class InitializeCustomBeardifierFieldsDuringForStructuresInChunkTransformer implements ITransformer<MethodNode> {

	@Override
	public @NotNull MethodNode transform(MethodNode node, ITransformerVotingContext context) {
		ASMUtil.findInstructions(
			node,
			Opcodes.ARETURN
		).forEach(target -> node.instructions.insertBefore(
			target,
			ASMAPI.listOf(
				new InsnNode(Opcodes.DUP), // Need to duplicate since we are not returning the object after consuming
				new VarInsnNode(Opcodes.ALOAD, 0), // StructureManager from params
				new VarInsnNode(Opcodes.ALOAD, 1), // ChunkPos from params
				new MethodInsnNode(
					Opcodes.INVOKESTATIC,
					"twilightforest/ASMHooks",
					"gatherCustomTerrain",
					"(Lnet/minecraft/world/level/StructureManager;Lnet/minecraft/world/level/ChunkPos;)Lit/unimi/dsi/fastutil/objects/ObjectListIterator;"
				),
				new FieldInsnNode(
					Opcodes.PUTFIELD, // pops the stack
					"net/minecraft/world/level/levelgen/Beardifier",
					"twilightforest_customStructureDensities",
					"Lit/unimi/dsi/fastutil/objects/ObjectListIterator;"
				)
			)
		));
		return node;
	}

	@Override
	public @NotNull TransformerVoteResult castVote(ITransformerVotingContext context) {
		return TransformerVoteResult.YES;
	}

	@Override
	public @NotNull Set<Target<MethodNode>> targets() {
		return Set.of(Target.targetMethod(
			"net.minecraft.world.level.levelgen.Beardifier",
			"forStructuresInChunk",
			"(Lnet/minecraft/world/level/StructureManager;Lnet/minecraft/world/level/ChunkPos;)Lnet/minecraft/world/level/levelgen/Beardifier;"
		));
	}

	@Override
	public @NotNull TargetType<MethodNode> getTargetType() {
		return TargetType.METHOD;
	}

}