package xyz.hafemann.netheriteextras.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import xyz.hafemann.netheriteextras.item.ModItems;

@Mixin(AbstractHorseEntity.class)
public abstract class NetheriteHorseArmorHorseEntityMixin extends AnimalEntity {
    protected NetheriteHorseArmorHorseEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void setOnFireFromLava() {
        if (this.isFireImmune()) {
            return;
        }

        Item item;
        int setOnFireFor = 15;
        float damage = 4.0F;

        item = getBodyArmor().getItem();
        if (item == ModItems.NETHERITE_HORSE_ARMOR) {
            setOnFireFor = 6;
            damage = 1.5F;
        }

        this.setOnFireFor(setOnFireFor);
        DamageSource damageSource = this.getDamageSources().lava();
        if (this.damage(damageSource, damage)) {
            this.playSound(SoundEvents.ENTITY_GENERIC_BURN, 0.4F, 2.0F + this.random.nextFloat() * 0.4F);
        }
    }
}
