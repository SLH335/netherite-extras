package dev.slh.netheriteextras.item;

import java.util.List;
import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.DeathProtection;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import dev.slh.netheriteextras.NetheriteExtras;

public class ModItems {
	public static final ResourceKey<EquipmentAsset> NETHERITE_ASSET =
		ResourceKey.create(EquipmentAssets.ROOT_ID, NetheriteExtras.id("netherite"));

	public static final ArmorMaterial NETHERITE = new ArmorMaterial(
		8,
		ArmorMaterials.NETHERITE.defense(),
		ArmorMaterials.NETHERITE.enchantmentValue(),
		ArmorMaterials.NETHERITE.equipSound(),
		ArmorMaterials.NETHERITE.toughness(),
		ArmorMaterials.NETHERITE.knockbackResistance(),
		ModItemTags.NETHERITE_NUGGETS,
		NETHERITE_ASSET);

	public static final Item NETHERITE_NUGGET = register(
		ModItemIds.NETHERITE_NUGGET, Item::new,
		new Item.Properties().fireResistant());

	public static final Item NETHERITE_WOLF_ARMOR = register(
		ModItemIds.NETHERITE_WOLF_ARMOR, Item::new,
		new Item.Properties().wolfArmor(NETHERITE).fireResistant());

	public static final Item TOTEM_OF_NEVERDYING = register(
		ModItemIds.TOTEM_OF_NEVERDYING, Item::new,
		new Item.Properties().stacksTo(1).durability(4).rarity(Rarity.UNCOMMON).fireResistant()
			.component(DataComponents.DEATH_PROTECTION, DeathProtection.TOTEM_OF_UNDYING));

	public static final Item NETHERITE_APPLE = register(
		ModItemIds.NETHERITE_APPLE, Item::new,
		new Item.Properties().rarity(Rarity.RARE).fireResistant()
			.food(new FoodProperties.Builder().nutrition(6).saturationModifier(1.2F).alwaysEdible()
				.build(),
			Consumables.defaultFood()
				.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
					new MobEffectInstance(MobEffects.REGENERATION, 200, 1),
					new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3000, 0),
					new MobEffectInstance(MobEffects.ABSORPTION, 2400, 2))))
				.build()));

	public static final Item ENCHANTED_NETHERITE_APPLE = register(
		ModItemIds.ENCHANTED_NETHERITE_APPLE, Item::new,
		new Item.Properties().rarity(Rarity.EPIC).fireResistant()
			.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
			.food(new FoodProperties.Builder().nutrition(6).saturationModifier(1.2F).alwaysEdible()
				.build(),
			Consumables.defaultFood()
				.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
					new MobEffectInstance(MobEffects.REGENERATION, 800, 2),
					new MobEffectInstance(MobEffects.RESISTANCE, 10000, 1),
					new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 12000, 0),
					new MobEffectInstance(MobEffects.ABSORPTION, 4800, 5),
					new MobEffectInstance(MobEffects.SPEED, 200, 1),
					new MobEffectInstance(MobEffects.STRENGTH, 200, 1))))
				.build()));

	public static Item register(ResourceKey<Item> itemKey,
			Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
		Item item = itemFactory.apply(settings.setId(itemKey));

		Registry.register(BuiltInRegistries.ITEM, itemKey, item);

		return item;
	}

	public static void initialize() {
	}
}
