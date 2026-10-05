package dev.slh.netheriteextras;

import net.minecraft.resources.Identifier;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.slh.netheriteextras.config.ServerConfig;
import dev.slh.netheriteextras.fabric.ModCreativeTabs;
import dev.slh.netheriteextras.fabric.ModDefaultComponents;
import dev.slh.netheriteextras.fabric.ModLootTables;
import dev.slh.netheriteextras.fabric.ModRegistryAliases;
import dev.slh.netheriteextras.item.ModItems;

public class NetheriteExtras implements ModInitializer {
	public static final String MOD_ID = "netheriteextras";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Netherite Extras...");

		ServerConfig.initialize(FabricLoader.getInstance().getConfigDir());
		ModItems.initialize();
		ModRegistryAliases.initialize();
		ModCreativeTabs.initialize();
		ModDefaultComponents.initialize();
		ModLootTables.initialize();

		LOGGER.info("Initialized Netherite Extras");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
