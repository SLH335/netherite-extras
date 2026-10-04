package dev.slh.netheriteextras.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.animal.wolf.Wolf;

import dev.slh.netheriteextras.item.ModItems;

@Mixin(Wolf.class)
public class WolfArmorAbsorbMixin {
	@Inject(
		method = "canArmorAbsorb(Lnet/minecraft/world/damagesource/DamageSource;)Z",
		at = @At("HEAD"),
		cancellable = true
	)
	private void netheriteextras$absorbWithNetheriteArmor(DamageSource source,
			CallbackInfoReturnable<Boolean> cir) {
		Wolf wolf = (Wolf) (Object) this;

		if (wolf.getBodyArmorItem().is(ModItems.NETHERITE_WOLF_ARMOR)
				&& !source.is(DamageTypeTags.BYPASSES_WOLF_ARMOR)) {
			cir.setReturnValue(true);
		}
	}
}
