package dev.slh.netheriteextras.client.config;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.LabelOption;
import dev.isxander.yacl3.api.YetAnotherConfigLib;

import dev.slh.netheriteextras.config.ServerConfig;

public class ConfigScreen {
	public static Screen create(Screen parent) {
		YetAnotherConfigLib gui = ServerConfig.handler().generateGui();

		return YetAnotherConfigLib
			.createBuilder()
				.title(Component.translatable("text.config.netheriteextras.title"))
				.save(gui.saveFunction())
				.categories(gui.categories())
				.build()
			.generateScreen(parent);
	}

	public static Screen createUnavailable(Screen parent) {
		return YetAnotherConfigLib
			.createBuilder()
				.title(Component.translatable("text.config.netheriteextras.title"))
				.category(ConfigCategory.createBuilder()
					.name(Component.translatable("text.config.netheriteextras.category.unavailable"))
					.option(LabelOption.create(
						Component.translatable("text.config.netheriteextras.unavailable")))
					.build())
				.build()
			.generateScreen(parent);
	}
}
