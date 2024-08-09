package xyz.hafemann.netheriteextras;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.*;

@Modmenu(modId = "netheriteextras")
@Config(name = "netheriteextras", wrapperName = "ModConfig")
public class ConfigModel {
    @RangeConstraint(min = 0.0f, max = 1.0f)
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public float piglinBruteNetheriteNuggetDropChance = 0.5f;
}
