package xyz.hafemann.netheriteextras.item;

import net.minecraft.item.AnimalArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.util.Identifier;
import xyz.hafemann.netheriteextras.NetheriteExtras;

public class NetheriteWolfArmorItem extends AnimalArmorItem {
    public NetheriteWolfArmorItem(Settings settings) {
        super(ArmorMaterials.NETHERITE, Type.CANINE, true, settings);
    }

    @Override
    public Identifier getEntityTexture() {
        String entityTexture = "textures/entity/wolf/wolf_armor_netherite.png";
        return new Identifier(NetheriteExtras.MOD_ID, entityTexture);
    }
}
