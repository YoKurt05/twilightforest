package twilightforest.world.components.feature.templates;

import com.google.common.math.StatsAccumulator;
import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.StructureMode;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class TemplateFeature<T extends FeatureConfiguration> extends Feature<T> {
	public TemplateFeature(Codec<T> config) {
		super(config);
	}

	@Override // Loosely based on WorldGenFossils
	public final boolean place(FeaturePlaceContext<T> ctx) {
		WorldGenLevel world = ctx.level();
		BlockPos pos = ctx.origin();
		Random random = world.getRandom();

		StructureManager templateManager = world.getLevel().getServer().getStructureManager();
		StructureTemplate template = this.getTemplate(templateManager, random);

		if(template == null)
			return false;

		Rotation rotation = Rotation.getRandom(random);
		Mirror mirror = Util.getRandom(Mirror.values(), random);

		ChunkPos chunkpos = new ChunkPos(pos);
		BoundingBox structureMask = new BoundingBox(chunkpos.getMinBlockX(), world.getMinBuildHeight(), chunkpos.getMinBlockZ(), chunkpos.getMaxBlockX(), world.getMaxBuildHeight(), chunkpos.getMaxBlockZ());

		BlockPos posSnap = chunkpos.getWorldPosition().offset(0, pos.getY() + this.yLevelOffset(), 0);

		Vec3i transformedSize = template.getSize(rotation);
		int dx = random.nextInt(16 - transformedSize.getX());
		int dz = random.nextInt(16 - transformedSize.getZ());
		posSnap.offset(dx, 0, dz);

		BlockPos.MutableBlockPos startPos = new BlockPos.MutableBlockPos(posSnap.getX(), posSnap.getY(), posSnap.getZ());

		if (!offsetToAverageGroundLevel(world, startPos, transformedSize)) {
			return false;
		}

		BlockPos placementPos = template.getZeroPositionWithTransform(startPos, mirror, rotation);

        StructurePlaceSettings placementSettings = (new StructurePlaceSettings()).setMirror(mirror).setRotation(rotation).setBoundingBox(structureMask).setRandom(random);
        this.modifySettings(placementSettings.clearProcessors(), random);

		template.placeInWorld(world, placementPos, placementPos, placementSettings, random, 20);

		for (StructureTemplate.StructureBlockInfo info : template.filterBlocks(placementPos, placementSettings, Blocks.STRUCTURE_BLOCK))
            if (info.nbt != null && StructureMode.valueOf(info.nbt.getString("mode")) == StructureMode.DATA)
                this.processMarkers(info, world, rotation, mirror, random);

        this.postPlacement(world, random, templateManager, rotation, mirror, placementSettings, placementPos);

		return true;
	}

    @Nullable
	protected abstract StructureTemplate getTemplate(StructureManager templateManager, Random random);

	protected void modifySettings(StructurePlaceSettings settings, Random random) {
    }

	protected void processMarkers(StructureTemplate.StructureBlockInfo info, WorldGenLevel world, Rotation rotation, Mirror mirror, Random random) {
    }

    protected void postPlacement(WorldGenLevel world, Random random, StructureManager templateManager, Rotation rotation, Mirror mirror, StructurePlaceSettings placementSettings, BlockPos placementPos) {
    }

    protected int yLevelOffset() {
        return -1;
    }

    private static boolean offsetToAverageGroundLevel(WorldGenLevel world, BlockPos.MutableBlockPos startPos, Vec3i size) {
        StatsAccumulator heights = new StatsAccumulator();

        for (int dx = 0; dx < size.getX(); dx++) {
            for (int dz = 0; dz < size.getZ(); dz++) {

                int x = startPos.getX() + dx;
                int z = startPos.getZ() + dz;

                int y = world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);

                while (y >= 0) {
                    BlockState state = world.getBlockState(new BlockPos(x, y, z));
                    if (isBlockNotOk(state)) return false;
                    if (isBlockOk(state)) break;
                    y--;
                }

                if (y < 0) return false;

                heights.add(y);
            }
        }

        if (heights.populationStandardDeviation() > 2.0) {
            return false;
        }

        int baseY = (int) Math.round(heights.mean());
        int maxY = (int) heights.max();

        startPos.setY(baseY);

        return isAreaClear(world, startPos.above(maxY - baseY + 1), startPos.offset(size));
    }

    private static boolean isAreaClear(LevelAccessor world, BlockPos min, BlockPos max) {
        for (BlockPos pos : BlockPos.betweenClosed(min, max)) {
            if (!world.getBlockState(pos).getMaterial().isReplaceable()) {
                return false;
            }
        }
        return true;
    }

    private static boolean isBlockOk(BlockState state) {
        Material material = state.getMaterial();
        return material == Material.STONE || material == Material.DIRT || material == Material.GRASS || material == Material.SAND;
    }

    private static boolean isBlockNotOk(BlockState state) {
        Material material = state.getMaterial();
        return material == Material.WATER || material == Material.LAVA || state.getBlock() == Blocks.BEDROCK;
    }

    private static boolean isDataBlock(StructureTemplate.StructureBlockInfo info) {
	    return StructureMode.DATA.name().equals(info.nbt.getString("mode"));
    }
}
