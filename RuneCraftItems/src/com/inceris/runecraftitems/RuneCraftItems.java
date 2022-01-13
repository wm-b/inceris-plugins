package com.inceris.runecraftitems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RuneCraftItems extends JavaPlugin {
	
	public static boolean debug = false;

	@Override
	public void onEnable() {

		this.saveDefaultConfig();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new BlockBreakListener(), this);

	}

	@Override
	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("runecraftitems") || label.equalsIgnoreCase("rci")) {
			try {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6R&5C&9I&8] &6Rune&5Craft&9Items &fdeveloped by &cInceris &ffor &9RuneCraft.us"));
					return true;
				}
				
				if (args[0].equals("give")) {
					
					Player p = getServer().getPlayer(args[1]);

					ItemStack item = null;

					if (args[2].equals("superpick")) {
						item = ItemList.superpick(new ItemStack(Material.NETHERITE_PICKAXE));
					}

					p.getInventory().addItem(item);
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6R&5C&9I&8] &fGiven " + p.getName() + " " + item.getItemMeta().getDisplayName()));
					return true;

				} else if (args[0].equals("debug")) {
					
					debug = !debug;
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6R&5C&9I&8] &fDebug set to " + debug));
					return true;
					
				} else if (args[0].equalsIgnoreCase("reload")) {
					this.reloadConfig();
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6R&5C&9I&8] &aConfig reloaded."));
					return true;
				}
				
			} catch (Exception e) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6R&5C&9I&8] &cThere was a problem!"));
				e.printStackTrace();
			}
		}
		return false;
	}
}