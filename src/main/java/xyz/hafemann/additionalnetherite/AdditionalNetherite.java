package xyz.hafemann.additionalnetherite;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.hafemann.additionalnetherite.item.ModItems;

public class AdditionalNetherite implements ModInitializer {
	public static final String MOD_ID = "additionalnetherite";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
	}
}
