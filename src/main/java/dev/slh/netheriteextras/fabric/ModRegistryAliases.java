package dev.slh.netheriteextras.fabric;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import dev.slh.netheriteextras.NetheriteExtras;

// Vanilla added Netherite Horse Armor in 1.21.11, so Netherite Extras doesn't ship its own for
// versions above that. This alias migrates existing pre-1.21.11 item stacks to the vanilla item id
public class ModRegistryAliases {
	public static void initialize() {
		BuiltInRegistries.ITEM.addAlias(
			NetheriteExtras.id("netherite_horse_armor"),
			Identifier.withDefaultNamespace("netherite_horse_armor"));
	}
}
