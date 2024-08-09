package xyz.hafemann.netheriteextras.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.hafemann.netheriteextras.item.ModItems;

@Mixin(ClientPlayNetworkHandler.class)
public class TotemFloatingDisplayMixin {
    public TotemFloatingDisplayMixin() {}

    @Inject(
            method = "getActiveTotemOfUndying",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getStackInHand(Lnet/minecraft/util/Hand;)Lnet/minecraft/item/ItemStack;"),
            cancellable = true
    )
    private static void onGetActiveTotemOfUndying(PlayerEntity player, CallbackInfoReturnable<ItemStack> cir) {
        Hand[] hands = Hand.values();
        for (Hand hand : hands) {
            ItemStack itemStack = player.getStackInHand(hand);
            if (itemStack.isOf(ModItems.TOTEM_OF_NEVERDYING)) {
                cir.setReturnValue(itemStack);
            }
        }
    }
}
