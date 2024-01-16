package twilightforest.init;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import twilightforest.TwilightForestMod;
import twilightforest.world.components.structures.placements.LandmarkGridPlacement;

public class TFStructureSets {

	public static final ResourceKey<StructureSet> HEDGE_MAZE = registerKey("hedge_maze");
	public static final ResourceKey<StructureSet> QUEST_GROVE = registerKey("quest_grove");
	public static final ResourceKey<StructureSet> HOLLOW_HILL_SMALL = registerKey("small_hollow_hill");
	public static final ResourceKey<StructureSet> HOLLOW_HILL_MEDIUM = registerKey("medium_hollow_hill");
	public static final ResourceKey<StructureSet> HOLLOW_HILL_LARGE = registerKey("large_hollow_hill");
	public static final ResourceKey<StructureSet> NAGA_COURTYARD = registerKey("naga_courtyard");
	public static final ResourceKey<StructureSet> LICH_TOWER = registerKey("lich_tower");
	public static final ResourceKey<StructureSet> LABYRINTH = registerKey("labyrinth");
	public static final ResourceKey<StructureSet> HYDRA_LAIR = registerKey("hydra_lair");
	public static final ResourceKey<StructureSet> KNIGHT_STRONGHOLD = registerKey("knight_stronghold");
	public static final ResourceKey<StructureSet> DARK_TOWER = registerKey("dark_tower");
	public static final ResourceKey<StructureSet> YETI_CAVE = registerKey("yeti_cave");
	public static final ResourceKey<StructureSet> AURORA_PALACE = registerKey("aurora_palace");
	public static final ResourceKey<StructureSet> TROLL_CAVE = registerKey("troll_cave");
	public static final ResourceKey<StructureSet> FINAL_CASTLE = registerKey("final_castle");

	public static final ResourceKey<StructureSet> MUSHROOM_TOWER = registerKey("mushroom_tower");
	public static final ResourceKey<StructureSet> QUEST_ISLAND = registerKey("quest_island");
	public static final ResourceKey<StructureSet> DRUID_GROVE = registerKey("druid_grove");
	public static final ResourceKey<StructureSet> FLOATING_RUINS = registerKey("floating_ruins");
	public static final ResourceKey<StructureSet> WORLD_TREE = registerKey("world_tree");

	private static ResourceKey<StructureSet> registerKey(String name) {
		return ResourceKey.create(Registries.STRUCTURE_SET, TwilightForestMod.prefix(name));
	}

	public static void bootstrap(BootstapContext<StructureSet> context) {
		HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

		context.register(HEDGE_MAZE, new StructureSet(structures.getOrThrow(TFStructures.HEDGE_MAZE), LandmarkGridPlacement.forTag(TFLandmark.HEDGE_MAZE, false)));
		context.register(QUEST_GROVE, new StructureSet(structures.getOrThrow(TFStructures.QUEST_GROVE), LandmarkGridPlacement.forTag(TFLandmark.QUEST_GROVE, true)));
		context.register(HOLLOW_HILL_SMALL, new StructureSet(structures.getOrThrow(TFStructures.HOLLOW_HILL_SMALL), LandmarkGridPlacement.forTag(TFLandmark.SMALL_HILL, false)));
		context.register(HOLLOW_HILL_MEDIUM, new StructureSet(structures.getOrThrow(TFStructures.HOLLOW_HILL_MEDIUM), LandmarkGridPlacement.forTag(TFLandmark.MEDIUM_HILL, false)));
		context.register(HOLLOW_HILL_LARGE, new StructureSet(structures.getOrThrow(TFStructures.HOLLOW_HILL_LARGE), LandmarkGridPlacement.forTag(TFLandmark.LARGE_HILL, false)));
		context.register(NAGA_COURTYARD, new StructureSet(structures.getOrThrow(TFStructures.NAGA_COURTYARD), LandmarkGridPlacement.forTag(TFLandmark.NAGA_COURTYARD, false)));
		context.register(LICH_TOWER, new StructureSet(structures.getOrThrow(TFStructures.LICH_TOWER), LandmarkGridPlacement.forTag(TFLandmark.LICH_TOWER, false)));

		context.register(LABYRINTH, new StructureSet(structures.getOrThrow(TFStructures.LABYRINTH), LandmarkGridPlacement.forTag(TFLandmark.LABYRINTH, true)));
		context.register(HYDRA_LAIR, new StructureSet(structures.getOrThrow(TFStructures.HYDRA_LAIR), LandmarkGridPlacement.forTag(TFLandmark.HYDRA_LAIR, true)));
		context.register(KNIGHT_STRONGHOLD, new StructureSet(structures.getOrThrow(TFStructures.KNIGHT_STRONGHOLD), LandmarkGridPlacement.forTag(TFLandmark.KNIGHT_STRONGHOLD, true)));
		context.register(DARK_TOWER, new StructureSet(structures.getOrThrow(TFStructures.DARK_TOWER), LandmarkGridPlacement.forTag(TFLandmark.DARK_TOWER, true)));
		context.register(YETI_CAVE, new StructureSet(structures.getOrThrow(TFStructures.YETI_CAVE), LandmarkGridPlacement.forTag(TFLandmark.YETI_CAVE, true)));
		context.register(AURORA_PALACE, new StructureSet(structures.getOrThrow(TFStructures.AURORA_PALACE), LandmarkGridPlacement.forTag(TFLandmark.ICE_TOWER, true)));
		context.register(TROLL_CAVE, new StructureSet(structures.getOrThrow(TFStructures.TROLL_CAVE), LandmarkGridPlacement.forTag(TFLandmark.TROLL_CAVE, true)));
		context.register(FINAL_CASTLE, new StructureSet(structures.getOrThrow(TFStructures.FINAL_CASTLE), LandmarkGridPlacement.forTag(TFLandmark.FINAL_CASTLE, true)));

		// uncomment to include in mod-internal datapack, for worldgen
		//context.register(MUSHROOM_TOWER, new StructureSet(structures.getOrThrow(TFStructures.MUSHROOM_TOWER), BiomeGridLandmarkPlacement.forTag(TFLandmark.MUSHROOM_TOWER, true)));
		//context.register(QUEST_ISLAND, new StructureSet(structures.getOrThrow(TFStructures.QUEST_ISLAND), BiomeGridLandmarkPlacement.forTag(TFLandmark.QUEST_ISLAND, true)));
		//context.register(DRUID_GROVE, new StructureSet(structures.getOrThrow(TFStructures.DRUID_GROVE), BiomeGridLandmarkPlacement.forTag(TFLandmark.DRUID_GROVE, true)));
		//context.register(FLOATING_RUINS, new StructureSet(structures.getOrThrow(TFStructures.MUSHROOM_TOWER), BiomeGridLandmarkPlacement.forTag(TFLandmark.FLOATING_RUINS, true)));
		//context.register(WORLD_TREE, new StructureSet(structures.getOrThrow(TFStructures.MUSHROOM_TOWER), BiomeGridLandmarkPlacement.forTag(TFLandmark.WORLD_TREE, true)));
	}
}
