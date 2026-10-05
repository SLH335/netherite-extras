package dev.slh.netheriteextras.client.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import dev.slh.netheriteextras.client.config.ConfigScreen;
import net.minecraft.client.Minecraft;

public class ModMenuIntegration implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> Minecraft.getInstance().hasSingleplayerServer()
			? ConfigScreen.create(parent)
			: ConfigScreen.create(parent);
	}
}
