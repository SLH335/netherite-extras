package xyz.hafemann.netheriteextras.mixin;

import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.hafemann.netheriteextras.item.ModItems;

@Mixin(ArmorItem.class)
public class NetheriteNuggetArmorMixin {

    @Redirect(method = "canRepair", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;canRepair(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z"))
    public boolean canRepair(Item instance, ItemStack stack, ItemStack ingredient) {
        System.out.println(ingredient.getItem().toString());
        ArmorMaterial material = ((ArmorItem) instance).getMaterial().value();
        if (material.repairIngredient().get().test(Items.NETHERITE_INGOT.getDefaultStack())) {
            return ingredient.getItem().equals(ModItems.NETHERITE_NUGGET);
        } else {
            return material.repairIngredient().get().test(ingredient) || instance.canRepair(stack, ingredient);
        }
    }
}