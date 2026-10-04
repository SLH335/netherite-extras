package dev.slh.netheriteextras.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

@Mixin(LivingEntity.class)
public class TotemDeathProtectionMixin {
	@WrapOperation(
		method = "checkTotemDeathProtection(Lnet/minecraft/world/damagesource/DamageSource;)Z",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V")
	)
	private void netheriteextras$damageTotemInsteadOfConsuming(ItemStack stack, int amount,
			Operation<Void> original, @Local InteractionHand hand) {
		if (stack.isDamageableItem()) {
			stack.hurtAndBreak(amount, (LivingEntity) (Object) this, hand);
		} else {
			original.call(stack, amount);
		}
	}
}
