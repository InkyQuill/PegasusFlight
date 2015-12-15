/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.PluginCommand
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerMoveEvent
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.PluginManager
 *  org.bukkit.util.Vector
 */
package net.inkyquill.equestria.flight.listeners;

import net.inkyquill.equestria.flight.math.FlightBean;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import net.inkyquill.equestria.flight.PegasusAbilities;
import net.inkyquill.equestria.flight.handle.FlightHandler;

public class MoveListener
implements Listener {

    //TODO: add region here


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        PegasusAbilities plugin = (PegasusAbilities)Bukkit.getServer().getPluginManager().getPlugin("PegasusAbilities");
        plugin.getCommand("flight").getExecutor();
        Player player = event.getPlayer();
        FlightBean flightBean = new FlightBean(0, 0);
        synchronized (FlightHandler.hasteMap) {
            if (FlightHandler.hasteMap.containsKey(player)) {
                flightBean = FlightHandler.hasteMap.get(player);
            }

            if (flightBean.getDuration() > 0) {
                Vector newVec = player.getLocation().getDirection().multiply(flightBean.getPower());
                player.setVelocity(newVec);
                flightBean.setDuration(flightBean.getDuration() - 1);
                FlightHandler.hasteMap.put(player, flightBean);
            }
        }
    }
}

