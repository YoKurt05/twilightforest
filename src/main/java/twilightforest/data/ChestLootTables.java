package twilightforest.data;

import net.minecraft.Util;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import twilightforest.init.TFBlocks;
import twilightforest.init.TFEnchantments;
import twilightforest.init.TFItems;
import twilightforest.loot.TFLootTables;
import twilightforest.loot.conditions.UncraftingTableEnabledCondition;

import java.util.function.BiConsumer;

@SuppressWarnings("deprecation")
public class ChestLootTables implements LootTableSubProvider {

	@Override
	public void generate(BiConsumer<ResourceLocation, LootTable.Builder> register) {
		register.accept(TFLootTables.USELESS_LOOT.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								.add(LootItem.lootTableItem(Items.POPPY).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(Items.DANDELION).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(Items.FEATHER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
								.add(LootItem.lootTableItem(Items.WHEAT_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
								.add(LootItem.lootTableItem(Items.FLINT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
								.add(LootItem.lootTableItem(Items.CACTUS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
								.add(LootItem.lootTableItem(Items.SUGAR_CANE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(Items.SAND).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(Items.FLOWER_POT))
								.add(LootItem.lootTableItem(Items.BONE_MEAL))));

		register.accept(TFLootTables.HUT_JUNK.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(9))
								// Nothing from USELESS_LOOT, too many "green/lively"-looking loot. Needs to be dead or close to actual junk
								.add(LootItem.lootTableItem(Items.COBWEB).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(3))
								.add(LootItem.lootTableItem(Items.BOWL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))).setWeight(3))
								.add(LootItem.lootTableItem(Items.GLASS_BOTTLE).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4))).setWeight(3))
								.add(LootItem.lootTableItem(Items.POTION).apply(SetPotionFunction.setPotion(Potions.WATER)))
								.add(LootItem.lootTableItem(Blocks.COARSE_DIRT).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5))))
								.add(LootItem.lootTableItem(Items.PAPER).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 9))))
								.add(LootItem.lootTableItem(Items.SAND).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(3))
								.add(LootItem.lootTableItem(Items.FLINT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))).setWeight(3))
								.add(LootItem.lootTableItem(Items.WHEAT_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5))))
								.add(LootItem.lootTableItem(Items.BONE_MEAL))
								.add(LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8))).setWeight(3))
						).withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								.add(LootItem.lootTableItem(Items.SUSPICIOUS_STEW).setWeight(8).apply(SetStewEffectFunction.stewEffect()
										.withEffect(MobEffects.JUMP, UniformGenerator.between(7, 10))
										.withEffect(MobEffects.WEAKNESS, UniformGenerator.between(6, 8))
										.withEffect(MobEffects.BLINDNESS, UniformGenerator.between(5, 7))
										.withEffect(MobEffects.POISON, UniformGenerator.between(10, 20))
										.withEffect(MobEffects.SATURATION, UniformGenerator.between(7, 10))
										.withEffect(MobEffects.WITHER, UniformGenerator.between(6, 10))
								))
								.add(LootItem.lootTableItem(Items.SUSPICIOUS_STEW).setWeight(2))
								.add(LootItem.lootTableItem(Items.MUSHROOM_STEW).setWeight(4))
								.add(LootItem.lootTableItem(Items.BELL))
								.add(LootItem.lootTableItem(Items.IRON_NUGGET).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5))))
								.add(LootItem.lootTableItem(Items.GOLD_NUGGET).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
						));

		register.accept(TFLootTables.BASEMENT.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								.add(LootTableReference.lootTableReference(TFLootTables.USELESS_LOOT.lootTable).setWeight(25))
								//common loot
								.add(LootItem.lootTableItem(Items.POTION).apply(SetNbtFunction.setTag(Util.make(new CompoundTag(), (nbt) -> nbt.putString("Potion", "minecraft:water")))).setWeight(75))
								.add(LootItem.lootTableItem(Items.ROTTEN_FLESH).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))).setWeight(75))
								.add(LootItem.lootTableItem(Items.POISONOUS_POTATO).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))).setWeight(75))
								.add(LootItem.lootTableItem(Items.WHEAT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))).setWeight(75))
								.add(LootItem.lootTableItem(Items.POTATO).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))).setWeight(75))
								.add(LootItem.lootTableItem(Items.CARROT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))).setWeight(75))
								.add(LootItem.lootTableItem(Items.MELON_SLICE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))).setWeight(75))
								.add(LootItem.lootTableItem(Items.WATER_BUCKET).setWeight(75))
								.add(LootItem.lootTableItem(Items.TORCH).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))).setWeight(75))
								.add(LootItem.lootTableItem(Items.MUSHROOM_STEW).setWeight(75))
								.add(LootItem.lootTableItem(Items.MILK_BUCKET).setWeight(75))
								.add(LootItem.lootTableItem(Items.SWEET_BERRIES).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 7))).setWeight(75))
								.add(LootItem.lootTableItem(Items.MELON_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))).setWeight(75)))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(Items.BREAD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 8))))
								.add(LootItem.lootTableItem(Items.COOKED_BEEF).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))))
								.add(LootItem.lootTableItem(Items.COOKED_PORKCHOP).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 8))))
								.add(LootItem.lootTableItem(Items.BAKED_POTATO).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 8))))
								.add(LootItem.lootTableItem(Items.COOKED_CHICKEN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 10))))
								.add(LootItem.lootTableItem(Items.COOKED_COD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 8)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(LootItem.lootTableItem(Items.GLISTERING_MELON_SLICE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))).setWeight(75))
								.add(LootItem.lootTableItem(Items.APPLE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))).setWeight(75))
								.add(LootItem.lootTableItem(Items.MAP).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.CHARM_OF_KEEPING_1.value()).setWeight(75))
								//ultrarare loot
								.add(LootItem.lootTableItem(Items.GOLDEN_APPLE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))).setWeight(25))
								.add(LootItem.lootTableItem(Items.GOLDEN_CARROT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))).setWeight(25))
								.add(LootItem.lootTableItem(Items.CAKE).setWeight(25))
								.add(LootItem.lootTableItem(Items.OAK_BOAT).setWeight(25))
								.add(LootItem.lootTableItem(TFBlocks.HOLLOW_OAK_SAPLING.value().asItem()).setWeight(25))
								.add(LootItem.lootTableItem(TFItems.MUSIC_DISC_HOME.value()).setWeight(15))
								.add(LootItem.lootTableItem(TFItems.MUSIC_DISC_RADIANCE.value()).setWeight(15))));

		register.accept(TFLootTables.FOUNDATION_BASEMENT.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								.add(LootTableReference.lootTableReference(TFLootTables.USELESS_LOOT.lootTable).setWeight(25))
								//common loot
								.add(LootItem.lootTableItem(Items.POTION).apply(SetNbtFunction.setTag(Util.make(new CompoundTag(), (nbt) -> nbt.putString("Potion", "minecraft:water")))).setWeight(75))
								.add(LootItem.lootTableItem(Items.ROTTEN_FLESH).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))).setWeight(75))
								.add(LootItem.lootTableItem(Items.POISONOUS_POTATO).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))).setWeight(75))
								.add(LootItem.lootTableItem(Items.WATER_BUCKET).setWeight(75))
								.add(LootItem.lootTableItem(Items.TORCH).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))).setWeight(75))
								.add(LootItem.lootTableItem(Items.MILK_BUCKET).setWeight(75)))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(Items.WHEAT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))))
								.add(LootItem.lootTableItem(Items.POTION).apply(SetNbtFunction.setTag(Util.make(new CompoundTag(), (nbt) -> nbt.putString("Potion", "minecraft:awkward")))))
								.add(LootItem.lootTableItem(Items.POTION).apply(SetNbtFunction.setTag(Util.make(new CompoundTag(), (nbt) -> nbt.putString("Potion", "minecraft:mundane")))))
								.add(LootItem.lootTableItem(Items.POTION).apply(SetNbtFunction.setTag(Util.make(new CompoundTag(), (nbt) -> nbt.putString("Potion", "minecraft:thick")))))
								.add(LootItem.lootTableItem(Items.ROTTEN_FLESH).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))))
								.add(LootItem.lootTableItem(Items.MELON_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
								.add(LootItem.lootTableItem(Items.PUMPKIN_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(LootItem.lootTableItem(Items.GLISTERING_MELON_SLICE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))).setWeight(75))
								.add(LootItem.lootTableItem(Items.MAP).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.CHARM_OF_KEEPING_1.value()).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.MUSIC_DISC_THREAD.value()).setWeight(50))
								//ultrarare loot
								.add(LootItem.lootTableItem(Items.GOLDEN_APPLE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))).setWeight(25))
								.add(LootItem.lootTableItem(Items.GOLDEN_CARROT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))).setWeight(25))
								.add(LootItem.lootTableItem(Items.OAK_BOAT).setWeight(25))
								.add(LootItem.lootTableItem(TFBlocks.HOLLOW_OAK_SAPLING.value().asItem()).setWeight(25))));

		register.accept(TFLootTables.WELL.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								//common loot
								.add(LootItem.lootTableItem(Items.GOLD_NUGGET).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 5))).setWeight(75))
								.add(LootItem.lootTableItem(Items.IRON_NUGGET).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))).setWeight(75))
								.add(LootItem.lootTableItem(Items.STRING).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))).setWeight(75))
								.add(LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 7))).setWeight(75))
								.add(EmptyLootItem.emptyItem().setWeight(25)))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(Items.BUCKET))
								.add(LootItem.lootTableItem(Items.MELON_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(Items.PUMPKIN_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(Items.INK_SAC).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
								.add(LootItem.lootTableItem(Items.WATER_BUCKET))
								.add(LootItem.lootTableItem(Items.BOWL))
								.add(LootItem.lootTableItem(TFItems.RAW_IRONWOOD.value()))
								.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
								.add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
								.add(LootItem.lootTableItem(Items.COPPER_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(LootItem.lootTableItem(Items.SHEARS))
								.add(LootItem.lootTableItem(Items.SADDLE))
								.add(LootItem.lootTableItem(Items.DIAMOND))
								.add(LootItem.lootTableItem(Items.EXPERIENCE_BOTTLE).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4))))));

		register.accept(TFLootTables.FANCY_WELL.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								//common loot
								.add(LootItem.lootTableItem(Items.GOLD_NUGGET).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 15))))
								.add(LootItem.lootTableItem(Items.IRON_NUGGET).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 12))))
								.add(LootItem.lootTableItem(Items.STRING).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 7))))
								.add(LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 20)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(Items.BUCKET))
								.add(LootItem.lootTableItem(Items.WATER_BUCKET))
								.add(LootItem.lootTableItem(TFItems.RAW_IRONWOOD.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 7))))
								.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 5))))
								.add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 4))))
								.add(LootItem.lootTableItem(Items.COPPER_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 8)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(LootItem.lootTableItem(Items.EXPERIENCE_BOTTLE).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 9))).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.CHARM_OF_LIFE_1.value()).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.MUSIC_DISC_STEPS.value()).setWeight(50))
								//ultrarare loot
								.add(LootItem.lootTableItem(Items.EMERALD).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 9))).setWeight(25))
								.add(LootItem.lootTableItem(Items.DIAMOND).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 5))).setWeight(25))
								.add(LootItem.lootTableItem(TFItems.CHARM_OF_KEEPING_2.value()).setWeight(25))
								.add(LootItem.lootTableItem(TFItems.TRANSFORMATION_POWDER.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 17))).setWeight(25))));

		register.accept(TFLootTables.HEDGE_MAZE.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								.add(LootTableReference.lootTableReference(TFLootTables.USELESS_LOOT.lootTable).setWeight(25))
								//common loot
								.add(LootItem.lootTableItem(Blocks.OAK_PLANKS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(Blocks.BROWN_MUSHROOM).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(Blocks.RED_MUSHROOM).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(Items.WHEAT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(Items.STRING).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))).setWeight(75)))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(Items.MELON_SLICE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(Items.MELON_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(Items.PUMPKIN_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(Items.ARROW).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))))
								.add(LootItem.lootTableItem(TFBlocks.FIREFLY.value().asItem()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(LootItem.lootTableItem(Items.COBWEB).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(Items.APPLE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))).setWeight(75))
								.add(LootItem.lootTableItem(Items.SHEARS).setWeight(75))
								.add(LootItem.lootTableItem(Items.SADDLE).setWeight(75))
								.add(LootItem.lootTableItem(Items.BOW).setWeight(75))
								//ultrarare loot
								.add(LootItem.lootTableItem(Items.GOLDEN_APPLE).setWeight(25))
								.add(LootItem.lootTableItem(Items.MUSHROOM_STEW).setWeight(25))
								.add(LootItem.lootTableItem(Items.DIAMOND).setWeight(25))
								.add(LootItem.lootTableItem(Items.DIAMOND_HOE).setWeight(25))
								.add(LootItem.lootTableItem(TFItems.MUSIC_DISC_MOTION.value()).setWeight(15))));

		register.accept(TFLootTables.TREE_CACHE.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								.add(LootTableReference.lootTableReference(TFLootTables.USELESS_LOOT.lootTable).setWeight(25))
								//common loot
								.add(LootItem.lootTableItem(Items.POISONOUS_POTATO).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))).setWeight(75))
								.add(LootItem.lootTableItem(Items.WHEAT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))).setWeight(75))
								.add(LootItem.lootTableItem(Items.POTATO).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))).setWeight(75))
								.add(LootItem.lootTableItem(Items.CARROT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))).setWeight(75))
								.add(LootItem.lootTableItem(Items.MELON_SLICE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))).setWeight(75))
								.add(LootItem.lootTableItem(Items.MELON_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))).setWeight(75))
								.add(LootItem.lootTableItem(Items.WATER_BUCKET).setWeight(75))
								.add(LootItem.lootTableItem(Items.MILK_BUCKET).setWeight(75)))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(TFBlocks.TWILIGHT_OAK_SAPLING.value().asItem()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(TFBlocks.CANOPY_SAPLING.value().asItem()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(TFBlocks.MANGROVE_SAPLING.value().asItem()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(TFBlocks.DARKWOOD_SAPLING.value().asItem()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(TFBlocks.FIREFLY.value().asItem()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(LootItem.lootTableItem(Items.PUMPKIN_PIE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))).setWeight(75))
								.add(LootItem.lootTableItem(Items.APPLE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.CHARM_OF_KEEPING_1.value()).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.CHARM_OF_LIFE_1.value()).setWeight(75))
								//ultrarare loot
								.add(LootItem.lootTableItem(TFBlocks.HOLLOW_OAK_SAPLING.value().asItem()).setWeight(25))
								.add(LootItem.lootTableItem(TFBlocks.TIME_SAPLING.value().asItem()).setWeight(25))
								.add(LootItem.lootTableItem(TFBlocks.TRANSFORMATION_SAPLING.value().asItem()).setWeight(25))
								.add(LootItem.lootTableItem(TFBlocks.MINING_SAPLING.value().asItem()).setWeight(25))
								.add(LootItem.lootTableItem(TFBlocks.SORTING_SAPLING.value().asItem()).setWeight(25))
								.add(LootItem.lootTableItem(TFItems.MUSIC_DISC_SUPERSTITIOUS.value()).setWeight(25))));

		register.accept(TFLootTables.GRAVEYARD.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								//common loot
								.add(LootItem.lootTableItem(Items.PUMPKIN_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 32))))
								.add(LootItem.lootTableItem(Items.PUMPKIN_PIE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))))
								.add(LootItem.lootTableItem(TFItems.TORCHBERRIES.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 16)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(Items.DIAMOND).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))))
								.add(LootItem.lootTableItem(TFItems.MOON_DIAL.value())))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(LootItem.lootTableItem(TFItems.TRANSFORMATION_POWDER.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))))
								.add(LootItem.lootTableItem(TFBlocks.UNCRAFTING_TABLE.value().asItem()).when(UncraftingTableEnabledCondition.uncraftingTableEnabled()))
								.add(LootItem.lootTableItem(Items.GOLDEN_APPLE))));

		register.accept(TFLootTables.SMALL_HOLLOW_HILL.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								.add(LootTableReference.lootTableReference(TFLootTables.USELESS_LOOT.lootTable).setWeight(25))
								//common loot
								.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(Items.WHEAT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(Items.STRING).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(Items.BUCKET).setWeight(75)))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(Items.TORCH).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))))
								.add(LootItem.lootTableItem(Items.ARROW).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))))
								.add(LootItem.lootTableItem(Items.GUNPOWDER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(Items.BREAD))
								.add(LootItem.lootTableItem(TFItems.ORE_MAGNET.value())))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(LootItem.lootTableItem(TFItems.LIVEROOT.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))).setWeight(75))
								.add(LootItem.lootTableItem(Items.GOLD_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))).setWeight(75))
								.add(LootItem.lootTableItem(Items.IRON_PICKAXE).setWeight(75))
								//ultrarare loot
								.add(LootItem.lootTableItem(Items.DIAMOND).setWeight(25))
								.add(LootItem.lootTableItem(TFItems.TRANSFORMATION_POWDER.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))).setWeight(25))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_INGOT.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))).setWeight(25))
								.add(LootItem.lootTableItem(TFItems.MUSIC_DISC_FINDINGS.value()).setWeight(25))));

		register.accept(TFLootTables.MEDIUM_HOLLOW_HILL.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								.add(LootTableReference.lootTableReference(TFLootTables.USELESS_LOOT.lootTable).setWeight(25))
								//common loot
								.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(Items.CARROT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(Items.LADDER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))).setWeight(75))
								.add(LootItem.lootTableItem(Items.BUCKET).setWeight(75)))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(Items.BAKED_POTATO).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
								.add(LootItem.lootTableItem(Items.ARROW).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))))
								.add(LootItem.lootTableItem(Items.TORCH).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))))
								.add(LootItem.lootTableItem(TFItems.ORE_MAGNET.value()))
								.add(LootItem.lootTableItem(TFItems.IRONWOOD_INGOT.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(LootItem.lootTableItem(TFItems.MAGIC_MAP.value()).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.TRANSFORMATION_POWDER.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))).setWeight(75))
								.add(LootItem.lootTableItem(TFBlocks.UNCRAFTING_TABLE.value().asItem()).when(UncraftingTableEnabledCondition.uncraftingTableEnabled()).setWeight(75))
								//ultrarare loot
								.add(LootItem.lootTableItem(Items.DIAMOND).setWeight(25))
								.add(LootItem.lootTableItem(Items.EMERALD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))).setWeight(25))
								.add(LootItem.lootTableItem(TFItems.PEACOCK_FEATHER_FAN.value()).setWeight(25))
								.add(LootItem.lootTableItem(TFItems.CHARM_OF_LIFE_1.value()).setWeight(25))
								.add(LootItem.lootTableItem(TFItems.MUSIC_DISC_WAYFARER.value()).setWeight(25))));

		register.accept(TFLootTables.LARGE_HOLLOW_HILL.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								.add(LootTableReference.lootTableReference(TFLootTables.USELESS_LOOT.lootTable).setWeight(25))
								//common loot
								.add(LootItem.lootTableItem(Items.GOLD_NUGGET).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 9))).setWeight(75))
								.add(LootItem.lootTableItem(Items.POTATO).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(Items.COD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.TORCHBERRIES.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))).setWeight(75)))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(Items.TORCH).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))))
								.add(LootItem.lootTableItem(Items.ARROW).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))))
								.add(LootItem.lootTableItem(Items.GUNPOWDER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_INGOT.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(Items.PUMPKIN_PIE))
								.add(LootItem.lootTableItem(TFItems.ORE_MAGNET.value())))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(LootItem.lootTableItem(TFItems.TRANSFORMATION_POWDER.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.IRONWOOD_PICKAXE.value()).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.BLOCK_EFFICIENCY, ConstantValue.exactly(1)).withEnchantment(Enchantments.BLOCK_FORTUNE, ConstantValue.exactly(1))).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.MAGIC_MAP.value()).setWeight(75))
								//ultrarare loot
								.add(LootItem.lootTableItem(Items.DIAMOND).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))).setWeight(25))
								.add(LootItem.lootTableItem(TFItems.MOONWORM_QUEEN.value()).setWeight(25))
								.add(LootItem.lootTableItem(TFItems.CHARM_OF_LIFE_1.value()).setWeight(25))
								.add(LootItem.lootTableItem(TFItems.CHARM_OF_KEEPING_1.value()).setWeight(25))
								.add(LootItem.lootTableItem(TFBlocks.IRONWOOD_BLOCK.value().asItem()).setWeight(25))
								.add(LootItem.lootTableItem(TFItems.MUSIC_DISC_MAKER.value()).setWeight(20))));

		register.accept(TFLootTables.QUEST_GROVE.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								.add(LootItem.lootTableItem(Blocks.WHITE_WOOL))
								.add(LootItem.lootTableItem(Blocks.ORANGE_WOOL))
								.add(LootItem.lootTableItem(Blocks.MAGENTA_WOOL))
								.add(LootItem.lootTableItem(Blocks.LIGHT_BLUE_WOOL))
								.add(LootItem.lootTableItem(Blocks.YELLOW_WOOL))
								.add(LootItem.lootTableItem(Blocks.LIME_WOOL))
								.add(LootItem.lootTableItem(Blocks.PINK_WOOL))
								.add(LootItem.lootTableItem(Blocks.GRAY_WOOL))
								.add(LootItem.lootTableItem(Blocks.LIGHT_GRAY_WOOL))
								.add(LootItem.lootTableItem(Blocks.CYAN_WOOL))
								.add(LootItem.lootTableItem(Blocks.PURPLE_WOOL))
								.add(LootItem.lootTableItem(Blocks.BLUE_WOOL))
								.add(LootItem.lootTableItem(Blocks.BROWN_WOOL))
								.add(LootItem.lootTableItem(Blocks.GREEN_WOOL))
								.add(LootItem.lootTableItem(Blocks.RED_WOOL))
								.add(LootItem.lootTableItem(Blocks.BLACK_WOOL))));

		register.accept(TFLootTables.TOWER_LIBRARY.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(3))
								//common loot
								.add(LootItem.lootTableItem(Items.PAPER).apply(SetItemCountFunction.setCount(UniformGenerator.between(24, 56))))
								.add(LootItem.lootTableItem(Items.BOOK).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 12))))
								.add(LootItem.lootTableItem(Items.BOOK).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(5, 10))))
								.add(LootItem.lootTableItem(TFBlocks.CANOPY_BOOKSHELF.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 12))))
								.add(LootItem.lootTableItem(Items.WRITABLE_BOOK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
								.add(LootItem.lootTableItem(Items.LADDER).apply(SetItemCountFunction.setCount(UniformGenerator.between(24, 56)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(EmptyLootItem.emptyItem().setWeight(2))
								.add(LootItem.lootTableItem(Items.EXPERIENCE_BOTTLE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
								.add(LootItem.lootTableItem(Items.DIAMOND))
								.add(LootItem.lootTableItem(Items.WRITABLE_BOOK))
								.add(LootItem.lootTableItem(Items.BOOK).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20, 30))))
								.add(LootItem.lootTableItem(Items.BOOK).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20, 30)).allowTreasure()))));

		register.accept(TFLootTables.TOWER_ROOM.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								.add(LootTableReference.lootTableReference(TFLootTables.USELESS_LOOT.lootTable).setWeight(25))
								//common loot
								.add(LootItem.lootTableItem(Items.BONE).apply((SetItemCountFunction.setCount(UniformGenerator.between(4, 16)))).setWeight(75))
								.add(LootItem.lootTableItem(Items.STRING).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 8))).setWeight(75))
								.add(LootItem.lootTableItem(Items.TORCH).apply(SetItemCountFunction.setCount(UniformGenerator.between(9, 24))).setWeight(75))
								.add(LootItem.lootTableItem(Items.CANDLE).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 14))).setWeight(75))
								.add(LootItem.lootTableItem(Items.SUSPICIOUS_STEW).apply(
										SetStewEffectFunction.stewEffect()
												.withEffect(MobEffects.NIGHT_VISION, UniformGenerator.between(7.0F, 10.0F))
												.withEffect(MobEffects.JUMP, UniformGenerator.between(7.0F, 10.0F))
												.withEffect(MobEffects.WEAKNESS, UniformGenerator.between(6.0F, 8.0F))
												.withEffect(MobEffects.BLINDNESS, UniformGenerator.between(5.0F, 7.0F))
												.withEffect(MobEffects.POISON, UniformGenerator.between(10.0F, 20.0F))
												.withEffect(MobEffects.SATURATION, UniformGenerator.between(7.0F, 10.0F))).setWeight(75))
								.add(LootItem.lootTableItem(Items.GOLD_NUGGET).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 9))).setWeight(75))
								.add(LootItem.lootTableItem(Items.IRON_NUGGET).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 20))).setWeight(75))
								.add(LootItem.lootTableItem(Items.BREAD).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 15))).setWeight(75)))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(TFItems.TRANSFORMATION_POWDER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
								.add(LootItem.lootTableItem(TFItems.LIVEROOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 7))))
								.add(LootItem.lootTableItem(TFItems.MAGIC_MAP))
								.add(LootItem.lootTableItem(TFItems.FIREFLY).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 9))))
								.add(LootItem.lootTableItem(Items.AMETHYST_SHARD).apply((SetItemCountFunction.setCount(UniformGenerator.between(4, 13)))))
								.add(LootItem.lootTableItem(Items.ENDER_PEARL).apply((SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))))
								.add(LootItem.lootTableItem(Items.POTION).apply(SetNbtFunction.setTag(Util.make(new CompoundTag(), (nbt) -> nbt.putString("Potion", "minecraft:healing")))))
								.add(LootItem.lootTableItem(Items.POTION).apply(SetNbtFunction.setTag(Util.make(new CompoundTag(), (nbt) -> nbt.putString("Potion", "minecraft:regeneration")))))
								.add(LootItem.lootTableItem(Items.POTION).apply(SetNbtFunction.setTag(Util.make(new CompoundTag(), (nbt) -> nbt.putString("Potion", "minecraft:strength")))))
								.add(LootItem.lootTableItem(Items.GOLDEN_SWORD).apply(EnchantRandomlyFunction.randomApplicableEnchantment()))
								.add(LootItem.lootTableItem(Items.ENDER_PEARL).apply((SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))))
								.add(LootItem.lootTableItem(Items.BOOK).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20, 30)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(EmptyLootItem.emptyItem().setWeight(75))
								.add(LootItem.lootTableItem(Items.DIAMOND).setWeight(75))
								.add(LootItem.lootTableItem(Items.OBSIDIAN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(Items.GOLDEN_HELMET).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(10, 15))).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.CHARM_OF_LIFE_1).setWeight(75))
								.add(LootItem.lootTableItem(Items.BOOK).apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(20, 30)).allowTreasure()).setWeight(75))
								.add(LootItem.lootTableItem(Items.GOLDEN_AXE).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(20))).setWeight(75))
								//TODO XP Charm
								.add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE).setWeight(25))
								.add(LootItem.lootTableItem(TFItems.MOONWORM_QUEEN.value()).setWeight(25))
								.add(LootItem.lootTableItem(TFItems.PEACOCK_FEATHER_FAN.value()).setWeight(25))));

		register.accept(TFLootTables.TOWER_POTION.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								.add(LootItem.lootTableItem(Items.GLISTERING_MELON_SLICE).apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 6))))
								.add(LootItem.lootTableItem(Items.GHAST_TEAR).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
								.add(LootItem.lootTableItem(Items.FERMENTED_SPIDER_EYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(Items.BLAZE_POWDER).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 9))))
								.add(LootItem.lootTableItem(Items.GLASS_BOTTLE).apply(SetItemCountFunction.setCount(UniformGenerator.between(10, 25))))
								.add(LootItem.lootTableItem(Items.MAGMA_CREAM).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
								.add(LootItem.lootTableItem(Items.SUGAR).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 7))))
								.add(LootItem.lootTableItem(Items.GUNPOWDER).apply(SetItemCountFunction.setCount(UniformGenerator.between(8, 20))))
								.add(LootItem.lootTableItem(Items.NETHER_WART).apply(SetItemCountFunction.setCount(UniformGenerator.between(6, 13)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								.add(LootItem.lootTableItem(Items.GOLDEN_CARROT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
								.add(LootItem.lootTableItem(Items.REDSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 7))))
								.add(LootItem.lootTableItem(Items.GLOWSTONE_DUST).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 6))))
								.add(LootItem.lootTableItem(Items.SPLASH_POTION).apply(SetNbtFunction.setTag(Util.make(new CompoundTag(), (nbt) -> nbt.putString("Potion", "minecraft:healing")))))
								.add(LootItem.lootTableItem(Items.POTION).apply(SetNbtFunction.setTag(Util.make(new CompoundTag(), (nbt) -> nbt.putString("Potion", "minecraft:strength")))))
								.add(LootItem.lootTableItem(Items.POTION).apply(SetNbtFunction.setTag(Util.make(new CompoundTag(), (nbt) -> nbt.putString("Potion", "minecraft:fire_resistance")))))
								.add(LootItem.lootTableItem(TFItems.BRITTLE_FLASK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								.add(LootItem.lootTableItem(Items.GOLDEN_APPLE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))).setWeight(75))
								.add(LootItem.lootTableItem(Items.BLAZE_ROD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(Items.POTION).apply(SetNbtFunction.setTag(Util.make(new CompoundTag(), (nbt) -> nbt.putString("Potion", "minecraft:strong_healing")))).setWeight(75))
								.add(LootItem.lootTableItem(Items.POTION).apply(SetNbtFunction.setTag(Util.make(new CompoundTag(), (nbt) -> nbt.putString("Potion", "minecraft:swiftness")))).setWeight(75))
								.add(LootItem.lootTableItem(Items.POTION).apply(SetNbtFunction.setTag(Util.make(new CompoundTag(), (nbt) -> nbt.putString("Potion", "minecraft:regeneration")))).setWeight(75))
								.add(LootItem.lootTableItem(Items.BREWING_STAND).setWeight(25))
								.add(LootItem.lootTableItem(TFItems.GREATER_FLASK).setWeight(25))));

		//all values in this loot table have been halved so I can fill both chests that appear in the dead ends
		register.accept(TFLootTables.LABYRINTH_DEAD_END.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								.add(LootTableReference.lootTableReference(TFLootTables.USELESS_LOOT.lootTable).setWeight(25))
								//common loot
								.add(LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))).setWeight(75))
								.add(LootItem.lootTableItem(Items.COAL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))).setWeight(75))
								.add(LootItem.lootTableItem(Items.ARROW).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))).setWeight(75))
								.add(LootItem.lootTableItem(Items.MUSHROOM_STEW).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.MAZE_WAFER.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))).setWeight(75))
								.add(LootItem.lootTableItem(Items.PAPER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))).setWeight(75))
								.add(LootItem.lootTableItem(Items.LEATHER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))).setWeight(75)))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(Items.MILK_BUCKET))
								.add(LootItem.lootTableItem(Items.PAPER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
								.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
								.add(LootItem.lootTableItem(TFItems.IRONWOOD_INGOT.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(TFBlocks.FIREFLY.value().asItem()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
								.add(LootItem.lootTableItem(TFItems.CHARM_OF_KEEPING_1.value())))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(LootItem.lootTableItem(Items.GOLDEN_APPLE))
								.add(LootItem.lootTableItem(TFBlocks.RED_THREAD.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 18))))
								.add(LootItem.lootTableItem(Items.BLAZE_ROD))
								.add(LootItem.lootTableItem(Items.BOOK).apply(new EnchantRandomlyFunction.Builder().withEnchantment(TFEnchantments.FIRE_REACT.value())))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_INGOT.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))));

		register.accept(TFLootTables.LABYRINTH_ROOM.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								//common loot
								.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(Items.GUNPOWDER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(TFItems.IRONWOOD_INGOT.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(Items.MILK_BUCKET))
								.add(LootItem.lootTableItem(TFItems.MAZE_WAFER.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))))
								.add(LootItem.lootTableItem(TFBlocks.FIREFLY.value().asItem()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(TFItems.STEELEAF_INGOT.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))))
								.add(LootItem.lootTableItem(TFItems.CHARM_OF_KEEPING_1.value()))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_HELMET.value()))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_CHESTPLATE.value()))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_LEGGINGS.value()))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_BOOTS.value()))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_PICKAXE.value()))
								.add(LootItem.lootTableItem(TFItems.IRONWOOD_CHESTPLATE.value()))
								.add(LootItem.lootTableItem(TFItems.IRONWOOD_SWORD.value()))
								.add(LootItem.lootTableItem(TFBlocks.RED_THREAD.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(5.0F, 15.0F)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(LootItem.lootTableItem(TFItems.MAZE_MAP_FOCUS.value()))
								.add(LootItem.lootTableItem(Items.TNT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))
								.add(LootItem.lootTableItem(Items.BOOK).apply(new EnchantRandomlyFunction.Builder().withEnchantment(TFEnchantments.FIRE_REACT.value())))
								.add(LootItem.lootTableItem(Items.POTION).apply(SetNbtFunction.setTag(Util.make(new CompoundTag(), (nbt) -> nbt.putString("Potion", "minecraft:strong_healing")))))));

		register.accept(TFLootTables.LABYRINTH_VAULT.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								//common loot
								.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 9))))
								.add(LootItem.lootTableItem(Items.EMERALD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
								.add(LootItem.lootTableItem(TFItems.IRONWOOD_INGOT.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 9))))
								.add(LootItem.lootTableItem(TFItems.MAZE_WAFER.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))))
								.add(LootItem.lootTableItem(Items.POTION).apply(SetNbtFunction.setTag(Util.make(new CompoundTag(), (nbt) -> nbt.putString("Potion", "minecraft:strong_regeneration")))))
								.add(LootItem.lootTableItem(Items.POTION).apply(SetNbtFunction.setTag(Util.make(new CompoundTag(), (nbt) -> nbt.putString("Potion", "minecraft:strong_healing")))))
								.add(LootItem.lootTableItem(Items.POTION).apply(SetNbtFunction.setTag(Util.make(new CompoundTag(), (nbt) -> nbt.putString("Potion", "minecraft:strong_swiftness"))))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(Items.BOW).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.INFINITY_ARROWS, ConstantValue.exactly(1)).withEnchantment(Enchantments.PUNCH_ARROWS, ConstantValue.exactly(2))))
								.add(LootItem.lootTableItem(Items.BOW).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.POWER_ARROWS, ConstantValue.exactly(3)).withEnchantment(Enchantments.FLAMING_ARROWS, ConstantValue.exactly(1))))
								.add(LootItem.lootTableItem(Items.BOOK).apply(new EnchantRandomlyFunction.Builder().withEnchantment(TFEnchantments.FIRE_REACT.value())))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_INGOT.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_SHOVEL.value()).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.BLOCK_EFFICIENCY, ConstantValue.exactly(4)).withEnchantment(Enchantments.UNBREAKING, ConstantValue.exactly(2))))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_AXE.value()).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.BLOCK_EFFICIENCY, ConstantValue.exactly(5))))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_CHESTPLATE.value()).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.ALL_DAMAGE_PROTECTION, ConstantValue.exactly(3))))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_BOOTS.value()).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.ALL_DAMAGE_PROTECTION, ConstantValue.exactly(2))))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_LEGGINGS.value()).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.FIRE_PROTECTION, ConstantValue.exactly(4))))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_HELMET.value()).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.RESPIRATION, ConstantValue.exactly(3)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(LootItem.lootTableItem(Items.EMERALD_BLOCK))
								.add(LootItem.lootTableItem(Items.ENDER_CHEST))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_PICKAXE.value()).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.BLOCK_EFFICIENCY, ConstantValue.exactly(4)).withEnchantment(Enchantments.SILK_TOUCH, ConstantValue.exactly(1))))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_SWORD.value()).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.SHARPNESS, ConstantValue.exactly(4)).withEnchantment(Enchantments.KNOCKBACK, ConstantValue.exactly(2))))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_SWORD.value()).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.BANE_OF_ARTHROPODS, ConstantValue.exactly(5)).withEnchantment(Enchantments.FIRE_ASPECT, ConstantValue.exactly(2))))));

		//Same as the one above, but with a 100% chance to get a mazebreaker
		register.accept(TFLootTables.LABYRINTH_VAULT_JACKPOT.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								.add(LootTableReference.lootTableReference(TFLootTables.LABYRINTH_VAULT.lootTable)))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//jackpot guaranteed mazebreaker
								.add(LootItem.lootTableItem(TFItems.MAZEBREAKER_PICKAXE.value()).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.BLOCK_EFFICIENCY, ConstantValue.exactly(4)).withEnchantment(Enchantments.UNBREAKING, ConstantValue.exactly(3)).withEnchantment(Enchantments.BLOCK_FORTUNE, ConstantValue.exactly(2))))));

		register.accept(TFLootTables.STRONGHOLD_CACHE.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								//common loot
								.add(LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))))
								.add(LootItem.lootTableItem(Items.COAL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))))
								.add(LootItem.lootTableItem(Items.ARROW).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))))
								.add(LootItem.lootTableItem(TFItems.MAZE_WAFER.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 9))))
								.add(LootItem.lootTableItem(Items.BLUE_WOOL))
								.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(Items.BUCKET))
								.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))))
								.add(LootItem.lootTableItem(TFItems.IRONWOOD_INGOT.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))))
								.add(LootItem.lootTableItem(TFBlocks.FIREFLY.value().asItem()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
								.add(LootItem.lootTableItem(TFItems.CHARM_OF_KEEPING_1.value()))
								.add(LootItem.lootTableItem(TFItems.ARMOR_SHARD.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(LootItem.lootTableItem(TFItems.KNIGHTMETAL_INGOT.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 8))).setWeight(75))
								.add(LootItem.lootTableItem(Items.BOW).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(20))).setWeight(75))
								.add(LootItem.lootTableItem(Items.IRON_SWORD).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(20))).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.IRONWOOD_SWORD.value()).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15))).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_SWORD.value()).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(10))).setWeight(75))
								//ultrarare loot
								.add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.BANE_OF_ARTHROPODS, ConstantValue.exactly(4))).setWeight(25))
								.add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.SHARPNESS, ConstantValue.exactly(4))).setWeight(25))
								.add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.SMITE, ConstantValue.exactly(4))).setWeight(25))
								.add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.UNBREAKING, ConstantValue.exactly(2))).setWeight(25))
								.add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.ALL_DAMAGE_PROTECTION, ConstantValue.exactly(3))).setWeight(25))
								.add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.PROJECTILE_PROTECTION, ConstantValue.exactly(3))).setWeight(25))
								.add(LootItem.lootTableItem(Items.BOOK).apply(new EnchantRandomlyFunction.Builder().withEnchantment(TFEnchantments.DESTRUCTION.value())).setWeight(25))
								.add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.FALL_PROTECTION, ConstantValue.exactly(3))).setWeight(25))));

		register.accept(TFLootTables.STRONGHOLD_ROOM.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								.add(LootTableReference.lootTableReference(TFLootTables.USELESS_LOOT.lootTable).setWeight(25))
								//common loot
								.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(Items.GUNPOWDER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(Items.MILK_BUCKET).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.MAZE_WAFER.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.IRONWOOD_INGOT.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(TFBlocks.FIREFLY.value().asItem()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))).setWeight(75)))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(TFItems.STEELEAF_INGOT.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))))
								.add(LootItem.lootTableItem(TFItems.CHARM_OF_LIFE_1.value()))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_HELMET.value()))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_CHESTPLATE.value()))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_LEGGINGS.value()))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_BOOTS.value()))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_PICKAXE.value()))
								.add(LootItem.lootTableItem(TFItems.IRONWOOD_CHESTPLATE.value()))
								.add(LootItem.lootTableItem(TFItems.IRONWOOD_SWORD.value())))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(LootItem.lootTableItem(TFItems.IRONWOOD_SWORD.value()).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(25))))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_SWORD.value()).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(20))))
								.add(LootItem.lootTableItem(Items.IRON_SWORD).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30))))
								.add(LootItem.lootTableItem(Items.BOW).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30))))
								.add(LootItem.lootTableItem(Items.DIAMOND_SWORD).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(15))))
								.add(LootItem.lootTableItem(Items.BOOK).apply(new EnchantRandomlyFunction.Builder().withEnchantment(TFEnchantments.DESTRUCTION.value())))
								.add(LootItem.lootTableItem(TFItems.MAZE_MAP_FOCUS.value()))));

		register.accept(TFLootTables.STRONGHOLD_BOSS.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								//common loot
								.add(LootItem.lootTableItem(TFItems.KNIGHTMETAL_SWORD.value()).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(20))))
								.add(LootItem.lootTableItem(TFItems.KNIGHTMETAL_PICKAXE.value()).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(20))))
								.add(LootItem.lootTableItem(TFItems.KNIGHTMETAL_AXE.value()).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(20)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(TFItems.PHANTOM_HELMET.value()).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(20))))
								.add(LootItem.lootTableItem(TFItems.PHANTOM_CHESTPLATE.value()).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(20)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(LootItem.lootTableItem(TFItems.PHANTOM_HELMET.value()).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30))))
								.add(LootItem.lootTableItem(TFItems.PHANTOM_CHESTPLATE.value()).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								.add(LootItem.lootTableItem(TFBlocks.KNIGHT_PHANTOM_TROPHY.value().asItem()))));

		register.accept(TFLootTables.DARKTOWER_CACHE.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								.add(LootTableReference.lootTableReference(TFLootTables.USELESS_LOOT.lootTable).setWeight(25))
								//common loot
								.add(LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))).setWeight(75))
								.add(LootItem.lootTableItem(Items.CHARCOAL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))).setWeight(75))
								.add(LootItem.lootTableItem(Items.ARROW).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.EXPERIMENT_115.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 9))).setWeight(75))
								.add(LootItem.lootTableItem(Items.RED_WOOL).setWeight(75))
								.add(LootItem.lootTableItem(Items.REDSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))).setWeight(75)))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(Items.REDSTONE_LAMP).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))))
								.add(LootItem.lootTableItem(TFItems.IRONWOOD_INGOT.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 8))))
								.add(LootItem.lootTableItem(TFBlocks.FIREFLY.value().asItem()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
								.add(LootItem.lootTableItem(TFItems.CHARM_OF_KEEPING_1.value())))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(LootItem.lootTableItem(TFItems.STEELEAF_INGOT.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 8))))
								.add(LootItem.lootTableItem(Items.DIAMOND).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))));

		register.accept(TFLootTables.DARKTOWER_KEY.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								.add(LootTableReference.lootTableReference(TFLootTables.USELESS_LOOT.lootTable).setWeight(25))
								//common loot
								.add(LootItem.lootTableItem(Items.IRON_INGOT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(Items.GUNPOWDER).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.IRONWOOD_INGOT.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(TFBlocks.FIREFLY.value().asItem()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.EXPERIMENT_115.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))).setWeight(75))
								.add(LootItem.lootTableItem(Items.GLOWSTONE_DUST).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))).setWeight(75))
								.add(LootItem.lootTableItem(Items.REDSTONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))).setWeight(75)))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(TFItems.STEELEAF_INGOT.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))))
								.add(LootItem.lootTableItem(TFItems.CHARM_OF_KEEPING_1.value()))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_HELMET.value()))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_CHESTPLATE.value()))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_LEGGINGS.value()))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_BOOTS.value()))
								.add(LootItem.lootTableItem(TFItems.STEELEAF_PICKAXE.value()))
								.add(LootItem.lootTableItem(TFItems.IRONWOOD_CHESTPLATE.value()))
								.add(LootItem.lootTableItem(TFItems.IRONWOOD_SWORD.value())))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(LootItem.lootTableItem(TFItems.CHARM_OF_LIFE_1.value()))
								.add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.FALL_PROTECTION, ConstantValue.exactly(3))))
								.add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.KNOCKBACK, ConstantValue.exactly(2))))
								.add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.BLOCK_EFFICIENCY, ConstantValue.exactly(3)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								.add(LootItem.lootTableItem(TFItems.TOWER_KEY.value()))));

		// Keeping this here for legacy generated chests
		register.accept(TFLootTables.DARKTOWER_BOSS.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								.add(LootItem.lootTableItem(TFItems.CARMINITE.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								.add(LootItem.lootTableItem(TFItems.FIERY_TEARS.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								.add(LootItem.lootTableItem(TFBlocks.UR_GHAST_TROPHY.value().asItem()))));

		register.accept(TFLootTables.AURORA_CACHE.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								.add(LootTableReference.lootTableReference(TFLootTables.USELESS_LOOT.lootTable).setWeight(25))
								//common loot
								.add(LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))).setWeight(75))
								.add(LootItem.lootTableItem(Items.COAL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))).setWeight(75))
								.add(LootItem.lootTableItem(Items.ARROW).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))).setWeight(75))
								.add(LootItem.lootTableItem(Items.ICE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(Items.PACKED_ICE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.IRONWOOD_INGOT.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.MAZE_WAFER.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 9))).setWeight(75)))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(TFItems.CHARM_OF_KEEPING_1.value()))
								.add(LootItem.lootTableItem(TFItems.IRONWOOD_INGOT.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))))
								.add(LootItem.lootTableItem(TFBlocks.FIREFLY.value().asItem()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
								.add(LootItem.lootTableItem(TFBlocks.AURORA_BLOCK.value().asItem()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))))
								.add(LootItem.lootTableItem(TFItems.ARCTIC_FUR.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(LootItem.lootTableItem(TFItems.ARCTIC_FUR.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 8))).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.ICE_BOW.value()).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.ENDER_BOW.value()).setWeight(75))
								.add(LootItem.lootTableItem(TFItems.ICE_SWORD.value()).setWeight(75))
								//ultrarare loot
								.add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.SHARPNESS, ConstantValue.exactly(4))).setWeight(25))
								.add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.POWER_ARROWS, ConstantValue.exactly(4))).setWeight(25))
								.add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.PUNCH_ARROWS, ConstantValue.exactly(2))).setWeight(25))
								.add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.UNBREAKING, ConstantValue.exactly(2))).setWeight(25))
								.add(LootItem.lootTableItem(Items.BOOK).apply(new EnchantRandomlyFunction.Builder().withEnchantment(TFEnchantments.CHILL_AURA.value())).setWeight(25))
								.add(LootItem.lootTableItem(Items.ENCHANTED_BOOK).apply(new SetEnchantmentsFunction.Builder(false).withEnchantment(Enchantments.INFINITY_ARROWS, ConstantValue.exactly(1))).setWeight(25))));

		register.accept(TFLootTables.AURORA_ROOM.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								//common loot
								.add(LootItem.lootTableItem(TFItems.MAZE_WAFER.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12))))
								.add(LootItem.lootTableItem(TFItems.ICE_BOMB.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(TFBlocks.FIREFLY.value().asItem()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 5))))
								.add(LootItem.lootTableItem(Items.ICE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(Items.PACKED_ICE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(TFItems.ARCTIC_FUR.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))))
								.add(LootItem.lootTableItem(TFItems.ARCTIC_HELMET.value()))
								.add(LootItem.lootTableItem(TFItems.ARCTIC_CHESTPLATE.value()))
								.add(LootItem.lootTableItem(TFItems.ARCTIC_LEGGINGS.value()))
								.add(LootItem.lootTableItem(TFItems.ARCTIC_BOOTS.value()))
								.add(LootItem.lootTableItem(TFItems.KNIGHTMETAL_CHESTPLATE.value()))
								.add(LootItem.lootTableItem(TFItems.KNIGHTMETAL_SWORD.value()))
								.add(LootItem.lootTableItem(TFItems.CHARM_OF_LIFE_1.value())))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(LootItem.lootTableItem(TFItems.ICE_BOW.value()).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(30))))
								.add(LootItem.lootTableItem(TFItems.ENDER_BOW.value()).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(5))))
								.add(LootItem.lootTableItem(TFItems.ICE_SWORD.value()).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(25))))
								.add(LootItem.lootTableItem(Items.BOOK).apply(new EnchantRandomlyFunction.Builder().withEnchantment(TFEnchantments.CHILL_AURA.value())))
								.add(LootItem.lootTableItem(TFItems.GLASS_SWORD.value()).apply(EnchantWithLevelsFunction.enchantWithLevels(ConstantValue.exactly(20))))));

		register.accept(TFLootTables.TROLL_GARDEN.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								//common loot
								.add(LootItem.lootTableItem(Items.RED_MUSHROOM).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(Items.BROWN_MUSHROOM).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 4))))
								.add(LootItem.lootTableItem(Items.WHEAT_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))))
								.add(LootItem.lootTableItem(Items.CARROT).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))))
								.add(LootItem.lootTableItem(Items.POTATO).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))))
								.add(LootItem.lootTableItem(Items.MELON_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))))
								.add(LootItem.lootTableItem(Items.BONE_MEAL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 12)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(TFBlocks.UBEROUS_SOIL.value().asItem()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								//rare loot
								.add(LootItem.lootTableItem(TFItems.MAGIC_BEANS.value()))));

		register.accept(TFLootTables.TROLL_VAULT.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(4))
								//common loot
								.add(LootItem.lootTableItem(Items.COAL).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 16))))
								.add(LootItem.lootTableItem(TFItems.TORCHBERRIES.value()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 16))))
								.add(LootItem.lootTableItem(Items.EMERALD).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6)))))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(2))
								//uncommon loot
								.add(LootItem.lootTableItem(TFBlocks.TROLLSTEINN.value().asItem()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))))
								.add(LootItem.lootTableItem(Items.OBSIDIAN).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 6))))));

		register.accept(TFLootTables.TROLL_VAULT_WITH_LAMP.lootTable,
				LootTable.lootTable()
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								.add(LootTableReference.lootTableReference(TFLootTables.TROLL_VAULT.lootTable)))
						.withPool(LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1))
								.add(LootItem.lootTableItem(TFItems.LAMP_OF_CINDERS.value()))));
	}
}
