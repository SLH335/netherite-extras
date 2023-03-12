package xyz.hafemann.netheriteextras.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import xyz.hafemann.netheriteextras.NetheriteExtras;

public class ModItems {

    public static final Item NETHERITE_NUGGET = registerItem("netherite_nugget",
            new Item(new Item.Settings().fireproof()));
    public static final Item NETHERITE_HORSE_ARMOR = registerItem("netherite_horse_armor",
            new NetheriteHorseArmorItem(15, "netherite", new HorseArmorItem.Settings().maxCount(1).fireproof()));
    public static final Item NETHERITE_APPLE = registerItem("netherite_apple",
            new Item(new Item.Settings().food((new FoodComponent.Builder()).hunger(6).saturationModifier(1.2F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 3000, 0), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 2), 1.0F)
                    .alwaysEdible().build()).fireproof()));
    public static final Item ENCHANTED_NETHERITE_APPLE = registerItem("enchanted_netherite_apple",
            new EnchantedGoldenAppleItem(new Item.Settings().food((new FoodComponent.Builder()).hunger(6).saturationModifier(1.2F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 800, 2), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 10000, 1), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 12000, 0), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 4800, 5), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 1), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 1), 1.0F)
                    .alwaysEdible().build()).fireproof()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(NetheriteExtras.MOD_ID, name), item);
    }

    public static void registerModItems() {
        NetheriteExtras.LOGGER.debug("Registering Mod Items for " + NetheriteExtras.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register(content -> content.addAfter(Items.GOLD_NUGGET, NETHERITE_NUGGET));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register(content -> content.addAfter(Items.DIAMOND_HORSE_ARMOR, NETHERITE_HORSE_ARMOR));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register(content -> content.addAfter(Items.ENCHANTED_GOLDEN_APPLE, NETHERITE_APPLE));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register(content -> content.addAfter(NETHERITE_APPLE, ENCHANTED_NETHERITE_APPLE));
    }
}
