package dev.slh.netheriteextras.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.item.ItemStack;

import dev.slh.netheriteextras.item.ModItems;

@Mixin(Wolf.class)
public class WolfArmorRepairMixin {
	private static final float NETHERITE_ARMOR_REPAIR_UNIT = 0.25F;

	@WrapOperation(
		method = "mobInteract(Lnet/minecraft/world/entity/player/Player;"
				+ "Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;setDamageValue(I)V")
	)
	private void netheriteextras$repairWolfArmor(ItemStack armor, int newDamage,
			Operation<Void> original) {
		if (armor.is(ModItems.NETHERITE_WOLF_ARMOR)) {
			int repairedDamage = (int) (armor.getMaxDamage() * NETHERITE_ARMOR_REPAIR_UNIT);
			newDamage = Math.max(0, armor.getDamageValue() - repairedDamage);
		}

		original.call(armor, newDamage);
	}
}
