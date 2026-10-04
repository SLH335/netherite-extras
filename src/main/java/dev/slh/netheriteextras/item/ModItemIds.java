package dev.slh.netheriteextras.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import dev.slh.netheriteextras.NetheriteExtras;

public class ModItemIds {
	public static final ResourceKey<Item> NETHERITE_NUGGET = create("netherite_nugget");
	public static final ResourceKey<Item> NETHERITE_WOLF_ARMOR = create("netherite_wolf_armor");
	public static final ResourceKey<Item> TOTEM_OF_NEVERDYING = create("totem_of_neverdying");
	public static final ResourceKey<Item> NETHERITE_APPLE = create("netherite_apple");
	public static final ResourceKey<Item> ENCHANTED_NETHERITE_APPLE =
			create("enchanted_netherite_apple");

	public static ResourceKey<Item> create(String name) {
		return ResourceKey.create(Registries.ITEM, NetheriteExtras.id(name));
	}
}
