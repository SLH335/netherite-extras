package dev.slh.netheriteextras.fabric;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;

import dev.slh.netheriteextras.item.ModItems;

public class ModLootTables {
	private static final float PIGLIN_BRUTE_NUGGET_DROP_CHANCE = 0.5F;

	public static void initialize() {
		LootTableEvents.MODIFY.register((key, tableBuilder, source, holder) -> {
			if (source.isBuiltin() && EntityTypes.PIGLIN_BRUTE.getDefaultLootTable()
					.filter(key::equals).isPresent()) {
				tableBuilder.withPool(LootPool.lootPool()
					.add(LootItem.lootTableItem(ModItems.NETHERITE_NUGGET))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(LootItemRandomChanceCondition
						.randomChance(PIGLIN_BRUTE_NUGGET_DROP_CHANCE)));
			}
		});
	}
}
