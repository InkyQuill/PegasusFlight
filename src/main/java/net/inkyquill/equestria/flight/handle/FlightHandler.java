package net.inkyquill.equestria.flight.handle;

import net.inkyquill.equestria.flight.math.FlightBean;
import net.inkyquill.equestria.flight.math.Region;
import net.inkyquill.equestria.flight.properties.Settings;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class FlightHandler {

    public static final Object lock = new Object();
    public static Map<Player, FlightBean> hasteMap = new HashMap<>();

    public static void disableFly(Player player) {
        player.setAllowFlight(false);
        player.setFlying(false);
    }

    public static boolean CheckRegion(Player p) {
        if (!p.hasPermission(Settings.FlightEnabled)) return true;
        if (!p.hasPermission(Settings.BypassRestrictions))
            for (Region reg : Settings.Regions.values()) {
                if (reg.inRegion(p)) {
                    return true;
                }
            }
        return false;
    }
}

