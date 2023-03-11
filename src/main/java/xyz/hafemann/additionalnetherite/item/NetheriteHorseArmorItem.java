package xyz.hafemann.additionalnetherite.item;

import net.minecraft.item.HorseArmorItem;
import net.minecraft.util.Identifier;
import xyz.hafemann.additionalnetherite.AdditionalNetherite;

public class NetheriteHorseArmorItem extends HorseArmorItem {

    private final String entityTexture;

    public NetheriteHorseArmorItem(int bonus, String name, Settings settings) {
        super(bonus, name, settings);
        this.entityTexture = "textures/entity/horse/armor/horse_armor_" + name + ".png";
    }

    @Override
    public Identifier getEntityTexture() {
        return new Identifier(AdditionalNetherite.MOD_ID, this.entityTexture);
    }

}
