package xyz.hafemann.netheriteextras;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.hafemann.netheriteextras.event.ModEvents;
import xyz.hafemann.netheriteextras.item.ModItems;
import xyz.hafemann.netheriteextras.ModConfig;

public class NetheriteExtras implements ModInitializer {
	public static final String MOD_ID = "netheriteextras";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final ModConfig CONFIG = ModConfig.createAndLoad();

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModEvents.registerModEvents();
	}
}
