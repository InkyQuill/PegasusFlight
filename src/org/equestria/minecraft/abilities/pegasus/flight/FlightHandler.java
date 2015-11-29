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
package org.equestria.minecraft.abilities.pegasus.flight;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.PluginManager;
import org.equestria.minecraft.abilities.pegasus.flight.Commands;
import org.equestria.minecraft.abilities.pegasus.flight.FlightBean;
import org.equestria.minecraft.abilities.pegasus.properties.FlightProperties;

public class FlightHandler {
    private static final String FLIGHT_PREFIX = "[Flight] ";
    private static final ChatColor YELLOW_COLOR = ChatColor.YELLOW;
    private static final ChatColor GREEN_COLOR = ChatColor.GREEN;
    private static final ChatColor RED_COLOR = ChatColor.RED;
    private static Map<String, Commands> commandsMap = new HashMap<String, Commands>();
    public static Map<Player, FlightBean> hasteMap = new HashMap<Player, FlightBean>();
    public static final Logger log = Logger.getLogger("FlightAbility");
    private static /* synthetic */ int[] $SWITCH_TABLE$org$equestria$minecraft$abilities$pegasus$flight$Commands;

    static {
        FlightHandler.initCommandsMap();
    }

    private static void initCommandsMap() {
        commandsMap.put("help", Commands.HELP);
        commandsMap.put("haste", Commands.HASTE);
        commandsMap.put("toggle", Commands.TOGGLE);
        commandsMap.put("on", Commands.ON);
        commandsMap.put("off", Commands.OFF);
        commandsMap.put("check", Commands.CHECK);
        commandsMap.put("rainboom", Commands.RAINBOOM);
    }

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

    public static boolean handleCommand(Player player, String stringCommand) {
        Commands command = commandsMap.get(stringCommand);
        if (command == null) {
            FlightHandler.handleNoArgsCommand(player);
            return false;
        }
        if (!FlightHandler.hasPermissionsToCommand(player, stringCommand)) {
            FlightHandler.noPermsMessage(player);
            return false;
        }
        switch (FlightHandler.$SWITCH_TABLE$org$equestria$minecraft$abilities$pegasus$flight$Commands()[command.ordinal()]) {
            case 1: {
                FlightHandler.helpCommand(player, stringCommand);
                return true;
            }
            case 6: {
                FlightHandler.checkCommand(player, stringCommand);
                return true;
            }
            case 3: {
                FlightHandler.flyHaste(player, stringCommand);
                return true;
            }
            case 5: {
                FlightHandler.flyOff(player, stringCommand);
                return true;
            }
            case 4: {
                FlightHandler.flyOn(player, stringCommand);
                return true;
            }
            case 7: {
                FlightHandler.flyRainboom(player, stringCommand);
                return true;
            }
            case 2: {
                FlightHandler.toggleCommand(player, stringCommand);
                return true;
            }
        }
        return false;
    }

    public static void noPermsMessage(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "You do not have permission for that command...");
    }

    private static void helpCommand(CommandSender sender, String args) {
        StringBuilder message = new StringBuilder();
        message.append("[Flight] ");
        message.append(GREEN_COLOR);
        message.append("Help Menu!");
        sender.sendMessage(message.toString());
        message = new StringBuilder();
        message.append(YELLOW_COLOR);
        message.append("[Flight] ");
        message.append(GREEN_COLOR);
        message.append("toggle - ");
        message.append(YELLOW_COLOR);
        message.append("Toggles flight.");
        sender.sendMessage(message.toString());
        message = new StringBuilder();
        message.append(YELLOW_COLOR);
        message.append("[Flight] ");
        message.append(GREEN_COLOR);
        message.append("on - ");
        message.append(YELLOW_COLOR);
        message.append("Enable flight.");
        sender.sendMessage(message.toString());
        message = new StringBuilder();
        message.append(YELLOW_COLOR);
        message.append("[Flight] ");
        message.append(GREEN_COLOR);
        message.append("off - ");
        message.append(YELLOW_COLOR);
        message.append("Disable flight.");
        sender.sendMessage(message.toString());
        message = new StringBuilder();
        message.append(YELLOW_COLOR);
        message.append("[Flight] ");
        message.append(GREEN_COLOR);
        message.append("haste - ");
        message.append(YELLOW_COLOR);
        message.append("Ehable haste.");
        sender.sendMessage(message.toString());
    }

    private static void toggleCommand(Player player, String args) {
        if (FlightHandler.isCreative(player)) {
            FlightHandler.sendCrativeModeMessage(player);
        } else if (player.getAllowFlight()) {
            FlightHandler.disableFly(player);
            FlightHandler.sendFlightOffMessage(player);
        } else {
            FlightHandler.enableFly(player);
            FlightHandler.sendFlightOnMessage(player);
            return;
        }
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

    private static void sendAlreadyOnMessage(Player player) {
        StringBuilder message = new StringBuilder();
        message.append("[Flight] ");
        message.append(RED_COLOR);
        message.append("Flight already on!");
        player.sendMessage(message.toString());
    }

    private static void sendAlreadyOffMessage(Player player) {
        StringBuilder message = new StringBuilder();
        message.append("[Flight] ");
        message.append(RED_COLOR);
        message.append("Flight already off!");
        player.sendMessage(message.toString());
    }

    private static void sendExhaustedMessage(Player player) {
        StringBuilder message = new StringBuilder();
        message.append("[Flight] ");
        message.append(YELLOW_COLOR);
        message.append("Too exhausted to haste!");
        player.sendMessage(message.toString());
    }

    private static void flyOn(Player player, String args) {
        if (FlightHandler.isCreative(player)) {
            FlightHandler.sendCrativeModeMessage(player);
        } else if (FlightHandler.flyModeEnabled(player)) {
            FlightHandler.sendAlreadyOnMessage(player);
        } else {
            FlightHandler.enableFly(player);
            FlightHandler.sendFlightOnMessage(player);
        }
    }

    private static void flyHaste(Player player, String args) {
        int foodLevel = player.getFoodLevel();
        if (foodLevel < FlightProperties.getInt("HasteExhaustLevel")) {
            FlightHandler.sendExhaustedMessage(player);
            return;
        }
        Map<Player, FlightBean> map = hasteMap;
        synchronized (map) {
            FlightBean flightBean = new FlightBean(FlightProperties.getInt("HasteDuration"), FlightProperties.getInt("HastePower"));
            hasteMap.put(player, flightBean);
            player.setFoodLevel(foodLevel - FlightProperties.getInt("HasteExhaustDecrement"));
            FoodLevelChangeEvent event = new FoodLevelChangeEvent(player, player.getFoodLevel());
            Bukkit.getServer().getPluginManager().callEvent(event);
        }
    }

    private static void flyRainboom(Player player, String args) {
        int foodLevel = player.getFoodLevel();
        if (foodLevel < FlightProperties.getInt("RainboomExhaustLevel")) {
            FlightHandler.sendExhaustedMessage(player);
            return;
        }
        Map<Player, FlightBean> map = hasteMap;
        synchronized (map) {
            FlightBean flightBean = new FlightBean(FlightProperties.getInt("RainboomDuration"), FlightProperties.getInt("RainboomPower"));
            hasteMap.put(player, flightBean);
            player.setFoodLevel(foodLevel - FlightProperties.getInt("RainboomExhaustDecrement"));
            FoodLevelChangeEvent event = new FoodLevelChangeEvent(player, player.getFoodLevel());
            Bukkit.getServer().getPluginManager().callEvent(event);
        }
    }

    private static void flyOff(Player player, String args) {
        if (FlightHandler.isCreative(player)) {
            FlightHandler.sendCrativeModeMessage(player);
            return;
        }
        if (!FlightHandler.flyModeEnabled(player)) {
            FlightHandler.sendAlreadyOffMessage(player);
            return;
        }
        FlightHandler.disableFly(player);
        FlightHandler.sendFlightOffMessage(player);
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

    private static void enableFly(Player player) {
        player.setAllowFlight(true);
        player.setFlying(true);
    }

    private static void disableFly(Player player) {
        player.setAllowFlight(false);
        player.setFlying(false);
    }

    private static boolean flyModeEnabled(Player player) {
        return player.getAllowFlight();
    }

    private static boolean isCreative(Player player) {
        return player.getGameMode() == GameMode.CREATIVE;
    }

    private static boolean hasPermissionsToCommand(Player player, String command) {
        return player.hasPermission(command);
    }

    static /* synthetic */ int[] $SWITCH_TABLE$org$equestria$minecraft$abilities$pegasus$flight$Commands() {
        int[] arrn;
        int[] arrn2 = $SWITCH_TABLE$org$equestria$minecraft$abilities$pegasus$flight$Commands;
        if (arrn2 != null) {
            return arrn2;
        }
        arrn = new int[Commands.values().length];
        try {
            arrn[Commands.CHECK.ordinal()] = 6;
        }
        catch (NoSuchFieldError v1) {}
        try {
            arrn[Commands.HASTE.ordinal()] = 3;
        }
        catch (NoSuchFieldError v2) {}
        try {
            arrn[Commands.HELP.ordinal()] = 1;
        }
        catch (NoSuchFieldError v3) {}
        try {
            arrn[Commands.OFF.ordinal()] = 5;
        }
        catch (NoSuchFieldError v4) {}
        try {
            arrn[Commands.ON.ordinal()] = 4;
        }
        catch (NoSuchFieldError v5) {}
        try {
            arrn[Commands.RAINBOOM.ordinal()] = 7;
        }
        catch (NoSuchFieldError v6) {}
        try {
            arrn[Commands.TOGGLE.ordinal()] = 2;
        }
        catch (NoSuchFieldError v7) {}
        $SWITCH_TABLE$org$equestria$minecraft$abilities$pegasus$flight$Commands = arrn;
        return $SWITCH_TABLE$org$equestria$minecraft$abilities$pegasus$flight$Commands;
    }
}

