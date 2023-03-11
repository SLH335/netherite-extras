package xyz.hafemann.additionalnetherite.mixin;

import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.hafemann.additionalnetherite.item.ModItems;

@Mixin(ToolMaterials.class)
public class NetheriteNuggetToolMixin {

    @Shadow
    private Lazy<Ingredient> repairIngredient;

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
