package com.inceris.lockout.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Util;

public class PlayerRespawnListener implements Listener {

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		GameInstance gi = GameInstance.get(p);
		if (gi != null) {
			if (gi.isActive() && gi.getPlayers().contains(p)) {
				p.getInventory().addItem(Util.compass);
			}
		}
	}

}
