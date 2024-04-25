package xyz.hafemann.netheriteextras.item;

import net.minecraft.item.AnimalArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.util.Identifier;
import xyz.hafemann.netheriteextras.NetheriteExtras;

public class NetheriteHorseArmorItem extends AnimalArmorItem {
    public NetheriteHorseArmorItem(Settings settings) {
        super(ArmorMaterials.NETHERITE, Type.EQUESTRIAN, false, settings);
    }

    @Override
    public Identifier getEntityTexture() {
        String entityTexture = "textures/entity/horse/armor/horse_armor_netherite.png";
        return new Identifier(NetheriteExtras.MOD_ID, entityTexture);
    }
}
