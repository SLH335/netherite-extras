package dev.slh.netheriteextras.fabric;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;

import dev.slh.netheriteextras.config.ServerConfig;
import dev.slh.netheriteextras.item.ModItems;

public class ModLootTables {
	public static void initialize() {
		LootTableEvents.MODIFY.register((key, tableBuilder, source, holder) -> {
			if (source.isBuiltin() && EntityTypes.PIGLIN_BRUTE.getDefaultLootTable()
					.filter(key::equals).isPresent()) {
				float dropChance = Mth.clamp(
					ServerConfig.get().piglinBruteNetheriteNuggetDropChance, 0.0F, 1.0F);

				tableBuilder.withPool(LootPool.lootPool()
					.add(LootItem.lootTableItem(ModItems.NETHERITE_NUGGET))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(LootItemRandomChanceCondition
						.randomChance(dropChance)));
			}
		});
	}
}
