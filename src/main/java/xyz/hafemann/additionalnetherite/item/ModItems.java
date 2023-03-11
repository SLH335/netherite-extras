package xyz.hafemann.additionalnetherite.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import xyz.hafemann.additionalnetherite.AdditionalNetherite;

public class ModItems {

    public static final Item NETHERITE_NUGGET = registerItem("netherite_nugget",
            new Item(new FabricItemSettings().fireproof()));
    public static final Item NETHERITE_HORSE_ARMOR = registerItem("netherite_horse_armor",
            new NetheriteHorseArmorItem(15, "netherite", new HorseArmorItem.Settings().maxCount(1).fireproof()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(AdditionalNetherite.MOD_ID, name), item);
    }

    public static void registerModItems() {
        AdditionalNetherite.LOGGER.debug("Registering Mod Items for " + AdditionalNetherite.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register(content -> content.addAfter(Items.GOLD_NUGGET, NETHERITE_NUGGET));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register(content -> content.addAfter(Items.DIAMOND_HORSE_ARMOR, NETHERITE_HORSE_ARMOR));
    }
}
