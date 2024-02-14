package xyz.hafemann.netheriteextras.mixin;

import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.hafemann.netheriteextras.item.ModItems;

@Mixin(UnbreakingEnchantment.class)
public class TotemUnbreakingEnchantableMixin {
    public TotemUnbreakingEnchantableMixin() {}

    @Inject(
            method = "isAcceptableItem",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onIsAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.isOf(ModItems.TOTEM_OF_NEVERDYING)) {
            cir.setReturnValue(false);
        }
    }
}
