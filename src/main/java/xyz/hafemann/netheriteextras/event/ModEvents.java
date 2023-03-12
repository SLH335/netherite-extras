package xyz.hafemann.netheriteextras.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import xyz.hafemann.netheriteextras.NetheriteExtras;
import xyz.hafemann.netheriteextras.item.ModItems;

public class ModEvents {
    public static void registerModEvents() {
        NetheriteExtras.LOGGER.debug("Registering Mod Events for " + NetheriteExtras.MOD_ID);

        ServerLivingEntityEvents.ALLOW_DEATH.register((LivingEntity livingEntity, DamageSource damageSource, float damageAmount) -> {
            for (Hand hand : Hand.values()) {
                ItemStack itemStack = livingEntity.getStackInHand(hand);
                if (itemStack.isOf(ModItems.TOTEM_OF_NEVERDYING)) {
                    if (livingEntity instanceof ServerPlayerEntity serverPlayerEntity) {
                        serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(ModItems.TOTEM_OF_NEVERDYING));
                        Criteria.USED_TOTEM.trigger(serverPlayerEntity, itemStack);
                    }

                    itemStack.setDamage(itemStack.getDamage() + 1);
                    if (itemStack.getDamage() >= itemStack.getMaxDamage()) itemStack.decrement(1);

                    livingEntity.setHealth(1.0F);
                    livingEntity.clearStatusEffects();
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
                    livingEntity.world.sendEntityStatus(livingEntity, (byte) 35);
                    return false;
                }
            }
            return true;
        });
    }
}
