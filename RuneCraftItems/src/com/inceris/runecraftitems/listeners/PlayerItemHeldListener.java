package com.inceris.runecraftitems.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.inceris.runecraftitems.util.Items;
import com.inceris.runecraftitems.util.Util;

public class PlayerItemHeldListener implements Listener {

	@EventHandler
	public void onPlayerItemHeld(PlayerItemHeldEvent e) {

		Player p = e.getPlayer();
		ItemStack itemInNewSlot = p.getInventory().getItem(e.getNewSlot());
		ItemStack itemInPreviousSlot = p.getInventory().getItem(e.getPreviousSlot());
		if (itemInNewSlot != null) {
			
			if (Util.checkItem(itemInNewSlot, Items.getItem("pulsingHeart"))) {
				Util.sendCommand("trailsid HEART " + p.getName());
				
			} else if (Util.checkItem(itemInNewSlot, Items.getItem("stargazer"))) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 60000000, 0));
				
			} else if (Util.checkItem(itemInNewSlot, Items.getItem("doubleEdgedSword"))) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 60000000, 0));
				
			} else if (Util.checkItem(itemInNewSlot, Items.getItem("runecraftEssence"))) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60000000, 0));
				
			} else if (Util.checkItem(itemInNewSlot, Items.getItem("poseidonstrident"))) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 60000000, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 60000000, 0));
				Util.sendCommand("trailsid WATER " + p.getName());
				
			} else if (Util.checkItem(itemInNewSlot, Items.getItem("mjolnir"))) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 60000000, 1));
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60000000, 0));
				
			}
			
		} 
		
		if (itemInPreviousSlot != null) {
			Util.checkRemoveItemEffects(p, itemInPreviousSlot);
		}
	}

}
