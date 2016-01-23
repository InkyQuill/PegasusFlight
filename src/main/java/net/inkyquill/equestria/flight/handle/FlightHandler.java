/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.GameMode
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Event
 *  org.bukkit.event.entity.FoodLevelChangeEvent
 *  org.bukkit.plugin.PluginManager
 */
package net.inkyquill.equestria.flight.handle;

import java.util.HashMap;
import java.util.Map;

import net.inkyquill.equestria.flight.math.FlightBean;
import net.inkyquill.equestria.flight.math.Region;
import net.inkyquill.equestria.flight.properties.Settings;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class FlightHandler {

    public static final Object lock = new Object();
    private static final ChatColor YELLOW_COLOR = ChatColor.YELLOW;
    private static final ChatColor GREEN_COLOR = ChatColor.GREEN;
    private static final ChatColor RED_COLOR = ChatColor.RED;
    public static Map<Player, FlightBean> hasteMap = new HashMap<Player, FlightBean>();

    public static void handleNoArgsCommand(Player player) {
        StringBuilder message = new StringBuilder();
        message.append("[Flight] ");
        message.append(GREEN_COLOR);
        player.sendMessage(message.toString());
        message = new StringBuilder();
        message.append(YELLOW_COLOR);
        message.append("- To view commands, do /flight ");
        message.append(GREEN_COLOR);
        message.append("help");
        player.sendMessage(message.toString());
    }

    public static void handleWrongArgsCommand(Player player) {
        StringBuilder message = new StringBuilder();
        message.append("[Flight] ");
        message.append(RED_COLOR);
        message.append("Too many arguments!");
        player.sendMessage(message.toString());
    }


    private static void sendFlightOnMessage(Player player) {
        StringBuilder message = new StringBuilder();
        message = new StringBuilder();
        message.append("[Flight] ");
        message.append(GREEN_COLOR);
        message.append("Flight enabled!");
        player.sendMessage(message.toString());
    }

    private static void sendFlightOffMessage(Player player) {
        StringBuilder message = new StringBuilder();
        message = new StringBuilder();
        message.append("[Flight] ");
        message.append(GREEN_COLOR);
        message.append("Flight disabled!");
        player.sendMessage(message.toString());
    }

    private static void sendCrativeModeMessage(Player player) {
        StringBuilder message = new StringBuilder();
        message.append("[Flight] ");
        message.append(RED_COLOR);
        message.append("Cannot change flight while in creative!");
        player.sendMessage(message.toString());
    }


    private static void checkCommand(Player player, String args) {
        if (FlightHandler.isCreative(player)) {
            FlightHandler.sendCrativeModeMessage(player);
            return;
        }
        if (FlightHandler.flyModeEnabled(player)) {
            FlightHandler.sendFlightOnMessage(player);
            return;
        }
        FlightHandler.sendFlightOffMessage(player);
    }

    public static void enableFly(Player player) {
        player.setAllowFlight(true);
        player.setFlying(true);
    }

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

    private static boolean flyModeEnabled(Player player) {
        return player.getAllowFlight();
    }

    private static boolean isCreative(Player player) {
        return player.getGameMode() == GameMode.CREATIVE;
    }

}

