package com.inceris.runecraftitems;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Util {
	
	private static RuneCraftItems rci = RuneCraftItems.getPlugin(RuneCraftItems.class);

	public static void SendCommand(String command) {
		ConsoleCommandSender console = rci.getServer().getConsoleSender();
		Bukkit.dispatchCommand(console, command);
	}
	
	public static ItemStack ConstructItem(Material material, String name) {
		ItemStack item = new ItemStack(material);
		item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 10);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', rci.getConfig().getString("items." + name + ".name")));
		List<String> lore = rci.getConfig().getStringList("items." + name + ".lore");
		for (int i = 0; i < lore.size(); i++) {
			lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
		}
		lore.add(0, "");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
}
