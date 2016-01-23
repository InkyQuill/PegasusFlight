
package net.inkyquill.equestria.flight.listeners;

import net.inkyquill.equestria.flight.handle.FlightHandler;
import net.inkyquill.equestria.flight.math.FlightBean;
import net.inkyquill.equestria.flight.properties.Settings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class MoveListener
        implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission(Settings.FlightEnabled)) {
            if (!FlightHandler.CheckRegion(player)) {
                player.setAllowFlight(true);
                FlightBean flightBean = new FlightBean(0, 0);
                synchronized (FlightHandler.lock) {
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
            } else {
                if (!player.hasPermission(Settings.BypassRestrictions)) {
                    FlightHandler.disableFly(player);
                }
            }
        }
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCancelFallDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL && p.hasPermission(Settings.NoFallDamage)) { //if the cause of damage is fall damage
                e.setCancelled(true); //you cancel the event.
            }
        }
    }
}

