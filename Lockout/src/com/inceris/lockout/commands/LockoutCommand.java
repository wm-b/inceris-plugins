package com.inceris.lockout.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Util;

public class LockoutCommand {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);
	
	public static boolean cmd(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(Util.format("Developed by &cInceris &ffor &9play.atownyserver.com"));
			return true;
			
		} else if (args[0].equalsIgnoreCase("start")) {
			if (pl.getServer().getPlayer(args[1]) != null && pl.getServer().getPlayer(args[2]) != null) {

				Player[] players = new Player[] { pl.getServer().getPlayer(args[1]),
						pl.getServer().getPlayer(args[2]) };
				
				for (Player p : players) {
					p.sendMessage(Util.format("Your game is starting! Please wait..."));
				}
				
				if (GameInstance.get(players[0]) == null && GameInstance.get(players[1]) == null) {
					new GameInstance(players[0], players[1]);
				} else {
					sender.sendMessage(Util.format("Existing game still active! Please wait and then try again."));
				}
				
			} else {
				sender.sendMessage(Util.format("&9/lockout start [player 1] [player 2]"));
				return true;
			}
			
		} else if (args[0].equalsIgnoreCase("stop")) {
			if (args.length == 1 && sender instanceof Player) {
				GameInstance.get((Player)sender).reset();
				return true;
			} else if (pl.getServer().getPlayer(args[1]) != null) {
				GameInstance.get(pl.getServer().getPlayer(args[1])).reset();
				return true;
			}
			
		} else if (args[0].equalsIgnoreCase("forcewin")) {
			if (pl.getServer().getPlayer(args[1]) != null && args.length > 1) {
				Util.stopGameWithWinner(pl.getServer().getPlayer(args[1]));
				return true;
			} else {
				sender.sendMessage(Util.format("&9/lockout forcewin [player]"));
			}
			
		} else if (args[0].equalsIgnoreCase("objectives")) {
			if (sender instanceof Player) {
				GameInstance gi = GameInstance.get((Player) sender);
				if (gi == null) {
					sender.sendMessage(Util.format("You're not in a game of lockout!"));
				} else {
					sender.sendMessage(Util.format(gi.printObjectives()));
				}
			}
			
		} else if (args[0].equalsIgnoreCase("info")) {
			if (args.length == 1) {
				if (sender instanceof Player) {
					GameInstance gi = GameInstance.get((Player) sender);
					if (gi == null) {
						sender.sendMessage(Util.format("You're not in a game of lockout!"));
					} else {
						sender.sendMessage(Util.format("World: " + gi.getWorld().getName()));
						sender.sendMessage(Util.format("Nether: " + gi.getNether().getName()));
						sender.sendMessage(Util.format("End: " + gi.getEnd().getName()));
						sender.sendMessage(Util.format("Active: " + gi.isActive()));
						sender.sendMessage(Util.format("Start Time: " + gi.getStartTime()));
					}
				}
			} else if (pl.getServer().getWorld(args[1]) != null) {
				GameInstance gi = GameInstance.get(pl.getServer().getWorld(args[1]));
				sender.sendMessage(Util.format("World: " + gi.getWorld().getName()));
				sender.sendMessage(Util.format("Active: " + gi.isActive()));
				sender.sendMessage(Util.format("Start Time: " + gi.getStartTime()));
			}
			
		} else {
			sender.sendMessage(Util.format("Developed by &cInceris &ffor &9play.atownyserver.com"));
			return true;
		}
		
		return false;
	}
	
}