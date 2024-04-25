package xyz.hafemann.netheriteextras.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.VariantHolder;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.WolfVariant;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.hafemann.netheriteextras.item.ModItems;

@Mixin(WolfEntity.class)
public abstract class NetheriteWolfArmorEquipMixin
        extends TameableEntity
        implements Angerable,
        VariantHolder<RegistryEntry<WolfVariant>> {
    protected NetheriteWolfArmorEquipMixin(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = "interactMob",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onInteractMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (this.isTamed()) {
            if (itemStack.isOf(ModItems.NETHERITE_WOLF_ARMOR) && this.isOwner(player) && this.getBodyArmor().isEmpty() && !this.isBaby()) {
                this.equipBodyArmor(itemStack.copyWithCount(1));
                itemStack.decrementUnlessCreative(1, player);
                cir.setReturnValue(ActionResult.SUCCESS);
            }
            if (Ingredient.ofItems(ModItems.NETHERITE_NUGGET).test(itemStack) && this.isInSittingPose()
                    && !this.getBodyArmor().isEmpty() && this.isOwner(player) && this.getBodyArmor().isDamaged()
                    && this.getBodyArmor().getItem().equals(ModItems.NETHERITE_WOLF_ARMOR)) {
                itemStack.decrement(1);
                this.playSoundIfNotSilent(SoundEvents.ITEM_WOLF_ARMOR_REPAIR);
                ItemStack bodyArmor = this.getBodyArmor();
                int amountRepaired = (int)((float)bodyArmor.getMaxDamage() * 0.25);
                bodyArmor.setDamage(Math.max(0, bodyArmor.getDamage() - amountRepaired));
                cir.setReturnValue(ActionResult.SUCCESS);
            }
            if (ArmorMaterials.ARMADILLO.value().repairIngredient().get().test(itemStack)
                    && this.getBodyArmor().getItem().equals(ModItems.NETHERITE_WOLF_ARMOR)) {
                cir.setReturnValue(ActionResult.PASS);
            }
        }
    }
}
