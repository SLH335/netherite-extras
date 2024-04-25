package xyz.hafemann.netheriteextras.mixin;

import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.hafemann.netheriteextras.item.ModItems;

import java.util.function.Supplier;

@Mixin(ToolMaterials.class)
public class NetheriteNuggetToolMixin {

    @Shadow
    private Supplier<Ingredient> repairIngredient;

    public NetheriteNuggetToolMixin() {
        this.repairIngredient = null;
    }

    @Inject(
        method = "getRepairIngredient",
        at = @At("HEAD"),
        cancellable = true
    )
    private void onGetRepairIngredient(CallbackInfoReturnable<Ingredient> ci) {
        if (this.repairIngredient != null) {
            if (this.repairIngredient.get().test(new ItemStack(Items.NETHERITE_INGOT))) {
                ci.setReturnValue(Ingredient.ofItems(ModItems.NETHERITE_NUGGET));
            }
        }
    }
}
