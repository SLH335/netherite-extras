package dev.slh.netheriteextras.fabric;

import java.util.List;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Repairable;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;

import dev.slh.netheriteextras.item.ModItemTags;

public class ModDefaultComponents {
	private static final List<Item> NETHERITE_TOOLS = List.of(
		Items.NETHERITE_SWORD, Items.NETHERITE_SHOVEL, Items.NETHERITE_PICKAXE,
		Items.NETHERITE_AXE, Items.NETHERITE_HOE, Items.NETHERITE_SPEAR
	);

	public static void initialize() {
		DefaultItemComponentEvents.MODIFY.register(context ->
			context.modify(NETHERITE_TOOLS, (builder, lookup, item) ->
				builder.set(DataComponents.REPAIRABLE, new Repairable(lookup.lookupOrThrow(Registries.ITEM)
					.getOrThrow(ModItemTags.REPAIRS_NETHERITE_EQUIPMENT)))));
	}
}
