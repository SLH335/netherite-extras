package dev.slh.netheriteextras.fabric;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

import dev.slh.netheriteextras.item.ModItems;

public class ModCreativeTabs {
	public static void initialize() {
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register((creativeTab) ->
			creativeTab.insertAfter(Items.GOLD_NUGGET, ModItems.NETHERITE_NUGGET));
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register((creativeTab) -> {
			creativeTab.insertAfter(Items.WOLF_ARMOR, ModItems.NETHERITE_WOLF_ARMOR);
			creativeTab.insertAfter(Items.TOTEM_OF_UNDYING, ModItems.TOTEM_OF_NEVERDYING);
		});
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
			.register((creativeTab) -> creativeTab.insertAfter(
				Items.ENCHANTED_GOLDEN_APPLE,
				ModItems.NETHERITE_APPLE,
				ModItems.ENCHANTED_NETHERITE_APPLE
			));
	}
}
