package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.PotionEffectType;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;

public class EntityPotionEffectListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	@EventHandler
	public void onEntityPotionEffect(EntityPotionEffectEvent e) {
		
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			GameInstance gi = GameInstance.get(p);
			if (gi != null) {
				if (gi.isActive() && gi.getPlayers().contains(p)) {
					List<Objective> objectives = gi.getObjectives();
					PotionEffectType pet = e.getModifiedType();
					
					if (pet.equals(PotionEffectType.ABSORPTION) && objectives.contains(Objective.gainAbsorption)) {
						Objective.complete(gi, Objective.gainAbsorption, p);
						
					} else if (pet.equals(PotionEffectType.DOLPHINS_GRACE) && objectives.contains(Objective.swimWithDolphins)) {
						Objective.complete(gi, Objective.swimWithDolphins, p);
						
					} else if (pet.equals(PotionEffectType.SLOW_DIGGING) && objectives.contains(Objective.getMiningFatigue)) {
						Objective.complete(gi, Objective.getMiningFatigue, p);
						
					} else if (pet.equals(PotionEffectType.LEVITATION) && objectives.contains(Objective.levitate)) {
						Objective.complete(gi, Objective.levitate, p);
						
					} else if (pet.equals(PotionEffectType.POISON) && objectives.contains(Objective.dontGetPoisoned)) {
						Objective.complete(gi, Objective.dontGetPoisoned, gi.getOpponents(p).get(0));
						
					} else if (pet.equals(PotionEffectType.SLOW) && objectives.contains(Objective.dontGetSlowed)) {
						Objective.complete(gi, Objective.dontGetSlowed, gi.getOpponents(p).get(0));
						
					}
				}
			}
		}
		
	}
	
}
