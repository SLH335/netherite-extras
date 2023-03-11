package xyz.hafemann.additionalnetherite.mixin;

import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.hafemann.additionalnetherite.item.ModItems;

@Mixin(ArmorMaterials.class)
public class NetheriteNuggetArmorMixin {
    @Shadow
    private Lazy<Ingredient> repairIngredientSupplier;

    public NetheriteNuggetArmorMixin() {
        this.repairIngredientSupplier = null;
    }

    @Inject(
        method = "getRepairIngredient",
        at = @At("HEAD"),
        cancellable = true
    )
    private void onGetRepairIngredient(CallbackInfoReturnable<Ingredient> ci) {
        if (this.repairIngredientSupplier.get().test(new ItemStack(Items.NETHERITE_INGOT))) {
            ci.setReturnValue(Ingredient.ofItems(ModItems.NETHERITE_NUGGET));
        }
    }
}