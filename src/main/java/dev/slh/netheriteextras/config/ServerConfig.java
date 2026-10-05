package dev.slh.netheriteextras.config;

import java.nio.file.Path;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.AutoGen;
import dev.isxander.yacl3.config.v2.api.autogen.FloatSlider;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;

import dev.slh.netheriteextras.NetheriteExtras;

public class ServerConfig {
	private static ConfigClassHandler<ServerConfig> handler;

	@AutoGen(category = "server")
	@FloatSlider(min = 0.0F, max = 1.0F, step = 0.01F, format = "%.2f")
	@SerialEntry(comment = "Chance of Piglin Brutes dropping a Netherite Nugget when killed by "
		+ "a player. Takes effect on the next datapack reload (/reload) or world load")
	public float piglinBruteNetheriteNuggetDropChance = 0.5F;

	public static void initialize(Path configDir) {
		handler = ConfigClassHandler.createBuilder(ServerConfig.class)
			.id(NetheriteExtras.id("server"))
			.serializer(config -> GsonConfigSerializerBuilder.create(config)
				.setPath(configDir.resolve("netheriteextras/server.json5"))
				.appendGsonBuilder(builder -> builder.setPrettyPrinting())
				.setJson5(true)
				.build())
			.build();

		if (handler.load()) {
			handler.save();
		} else {
			NetheriteExtras.LOGGER.warn("Failed to load server config, using defaults");
		}
	}

	public static ServerConfig get() {
		return handler.instance();
	}

	public static ConfigClassHandler<ServerConfig> handler() {
		return handler;
	}
}
