package com.inceris.atsutilities.commands;

import com.inceris.atsutilities.ATSUtilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Blink {

    private static final ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);

    public static boolean cmd(String[] args) {

        Bukkit.getScheduler().runTask(atsu, new Runnable() {
            @Override
            public void run() {
                Location destination = null;
                Block check;
                Player p = atsu.getServer().getPlayer(args[0]);
                if (p == null) return;
                Location location = p.getLocation();
                for (int i = 1; i < Integer.parseInt(args[1]); i++) {
                    check = p.getTargetBlock(null, i);
                    if (!check.getType().isSolid())
                        destination = check.getLocation();
                    else
                        break;
                }
                if (destination != null) {
                    destination.setPitch(location.getPitch());
                    destination.setYaw(location.getYaw());
                    if (destination.getY() > location.getY()) {
                        destination.setY(location.getY());
                    }
                    ATSRTP.TPPlayer(atsu, p, destination);
                } else
                    p.sendMessage(ChatColor.RED + "There is a wall in the way!");
            }
        });

        return true;
    }

}
