package dev.justnotro.electrohub.utils;

import dev.justnotro.electrohub.Electrohub;
import lombok.SneakyThrows;
import org.bukkit.Color;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityCategory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ItemBuilder {

	private static Plugin plugin = Electrohub.getInstance();

	private ItemStack itemstack;

	public ItemBuilder(Material type, int amount) {
		this.itemstack = new ItemStack(type, amount);
	}

	public ItemBuilder(Material type, short durability) {
		this.itemstack = new ItemStack(type, 1, durability);
	}

	public ItemBuilder(ItemStack itemStack) {
		this.itemstack = itemStack;
	}

	public ItemBuilder(Material type, String dispName) {
		this.itemstack = new ItemStack(type);
		ItemMeta itemMeta = itemstack.getItemMeta();
		itemMeta.setDisplayName(fixColor(dispName));
		this.itemstack.setItemMeta(itemMeta);
	}

	public ItemBuilder addLoreLines(String... lines) {
		ItemMeta itemMeta = this.itemstack.getItemMeta();
		List<String> loreList = itemMeta.getLore() != null ? itemMeta.getLore() : new ArrayList<>();
		Arrays.stream(lines).forEachOrdered(lore -> loreList.add(fixColor("&7" + lore)));
		itemMeta.setLore(loreList);
		this.itemstack.setItemMeta(itemMeta);
		return this;
	}

	public ItemBuilder setType(Material type) {
		this.itemstack.setType(type);
		return this;
	}

	public ItemBuilder setDisplayName(String newDisplayName) {
		ItemMeta itemMeta = this.itemstack.getItemMeta();
		itemMeta.setDisplayName(fixColor(newDisplayName));
		this.itemstack.setItemMeta(itemMeta);
		return this;
	}

	public ItemBuilder setAmount(int amount) {
		this.itemstack.setAmount(amount);
		return this;
	}

	public ItemBuilder setLore(List<String> lore) {
		List<String> newLore = new ArrayList<>();
		lore.forEach(string -> {
			newLore.add(fixColor(string));
		});
		ItemMeta im = this.itemstack.getItemMeta();
		im.setLore(newLore);
		this.itemstack.setItemMeta(im);
		return this;
	}

	public ItemBuilder setLoreline(String newLine, int lineToReplace) {
		List<String> currentLore = itemstack.getItemMeta().getLore();
		currentLore.set(lineToReplace, fixColor(newLine));
		ItemMeta itemMeta = itemstack.getItemMeta();
		itemMeta.setLore(currentLore);
		itemstack.setItemMeta(itemMeta);
		return this;
	}

	public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
		this.itemstack.addUnsafeEnchantment(enchantment, level);
		return this;
	}

	public ItemBuilder removeEnchantment(Enchantment enchantment) {
		this.itemstack.removeEnchantment(enchantment);
		return this;
	}

	public ItemBuilder addFlag(ItemFlag flag) {
		this.itemstack.getItemMeta().addItemFlags(flag);
		return this;
	}

	@SneakyThrows
	public ItemBuilder setArmourColor(Color color) {
		LeatherArmorMeta itemMeta = (LeatherArmorMeta) itemstack.getItemMeta();
		itemMeta.setColor(color);
		this.itemstack.setItemMeta(itemMeta);
		return this;
	}

	public ItemBuilder setCustomModelData(int dataModel) {
		ItemMeta im = itemstack.getItemMeta();
		im.setCustomModelData(dataModel);
		itemstack.setItemMeta(im);
		return this;
	}

	@SneakyThrows
	public ItemBuilder setPlayerSkull(OfflinePlayer value) {
		itemstack.setType(Material.PLAYER_HEAD);
		SkullMeta itemMeta = (SkullMeta) itemstack.getItemMeta();
		itemMeta.setOwningPlayer(value);
		itemstack.setItemMeta(itemMeta);
		return this;
	}

	/**
	 * This method requires class EnchatmentGlow.java
	 */
	public ItemBuilder addGlow() {
		itemstack.addUnsafeEnchantment(EnchantGlow.getGlow(), 1);
		return this;
	}

	public ItemStack create() {
		return itemstack;
	}

	private String fixColor(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}

	public static class EnchantGlow extends Enchantment {

		private static Enchantment glow;

		public EnchantGlow(NamespacedKey id) {
			super(id);
		}

		@Override
		public boolean canEnchantItem(ItemStack item) {
			return true;
		}

		@Override
		public boolean conflictsWith(Enchantment other) {
			return false;
		}

		@Override
		public EnchantmentTarget getItemTarget() {
			return null;
		}

		@Override
		public int getMaxLevel() {
			return 10;
		}

		@Override
		public String getName() {
			return "Glow";
		}

		@Override
		public int getStartLevel() {
			return 1;
		}

		@Override
		public boolean isTreasure() {
			return false;
		}

		@Override
		public boolean isCursed() {
			return false;
		}

		public static Enchantment getGlow() {
			try {
				Field f = Enchantment.class.getDeclaredField("acceptingNew");
				f.setAccessible(true);
				f.set(null, true);
			} catch (Exception e) {
				e.printStackTrace();
			}

			glow = new EnchantGlow(new NamespacedKey(plugin, "glow"));
			if (Enchantment.getByKey(NamespacedKey.fromString("glow", plugin)) == null) Enchantment.registerEnchantment(glow);
			return glow;
		}


		public @NotNull Component displayName(int level) {
			return null;
		}

		public boolean isTradeable() {
			return false;
		}

		public boolean isDiscoverable() {
			return false;
		}

		public float getDamageIncrease(int level, @NotNull EntityCategory entityCategory) {
			return 0;
		}

		public @NotNull Set<EquipmentSlot> getActiveSlots() {
			return null;
		}

		public @NotNull String translationKey() {
			return null;
		}
	}

}
