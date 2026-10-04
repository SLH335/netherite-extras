package dev.slh.netheriteextras.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import dev.slh.netheriteextras.NetheriteExtras;

public class ModItemTags {
	public static final TagKey<Item> NETHERITE_NUGGETS = conventional("nuggets/netherite");
	public static final TagKey<Item> REPAIRS_NETHERITE_EQUIPMENT = of("repairs_netherite_equipment");

	private static TagKey<Item> conventional(String path) {
		return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", path));
	}

	private static TagKey<Item> of(String path) {
		return TagKey.create(Registries.ITEM, NetheriteExtras.id(path));
	}
}
