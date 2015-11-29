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
package org.equestria.minecraft.abilities.pegasus.flight;

import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.util.Vector;
import org.equestria.minecraft.abilities.pegasus.PegasusAbilities;
import org.equestria.minecraft.abilities.pegasus.flight.FlightBean;
import org.equestria.minecraft.abilities.pegasus.flight.FlightHandler;

public class MoveListener
implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        PegasusAbilities plugin = (PegasusAbilities)Bukkit.getServer().getPluginManager().getPlugin("PegasusAbilities");
        plugin.getCommand("flight").getExecutor();
        Player player = event.getPlayer();
        FlightBean flightBean = new FlightBean(0, 0);
        Map<Player, FlightBean> map = FlightHandler.hasteMap;
        synchronized (map) {
            if (FlightHandler.hasteMap.containsKey((Object)player)) {
                flightBean = FlightHandler.hasteMap.get((Object)event.getPlayer());
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

