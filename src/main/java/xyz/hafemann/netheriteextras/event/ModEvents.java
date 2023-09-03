package xyz.hafemann.netheriteextras.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import xyz.hafemann.netheriteextras.NetheriteExtras;
import xyz.hafemann.netheriteextras.item.ModItems;

public class ModEvents {
    public static void registerModEvents() {
        NetheriteExtras.LOGGER.debug("Registering Mod Events for " + NetheriteExtras.MOD_ID);

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (id.equals(EntityType.PIGLIN_BRUTE.getLootTableId())) {
                supplier.pool(LootPool.builder()
                        .rolls(BinomialLootNumberProvider.create(1, 0.5F))
                        .with(ItemEntry.builder(ModItems.NETHERITE_NUGGET).build()).build());
            }
        });

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
                    livingEntity.getWorld().sendEntityStatus(livingEntity, (byte) 35);
                    return false;
                }
            }
            return true;
        });
    }
}
