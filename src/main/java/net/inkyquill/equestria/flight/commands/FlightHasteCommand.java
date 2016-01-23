package net.inkyquill.equestria.flight.commands;

import net.inkyquill.equestria.flight.math.FlightBean;
import net.inkyquill.equestria.flight.properties.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FlightHasteCommand implements CommandExecutor {

    static String PIntro = ChatColor.GOLD + "[Flight/Haste] ";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof ConsoleCommandSender) {
            commandSender.sendMessage(PIntro + ChatColor.RED + "Консоль не может летать!");
            return true;
        }
        Player p = (Player) commandSender;
        if (!p.hasPermission(Settings.FlightEnabled)) {
            p.sendMessage(PIntro + ChatColor.RED + "Извините, вы не можете ускориться.");
            return true;
        }
        if (command.getName().toLowerCase().equals("haste")) {
            DoHaste(p);
        } else {
            DoRainboom(p);
        }
        return false;
    }

    private void DoRainboom(Player player) {
        int foodLevel = player.getFoodLevel();
        if (foodLevel < Settings.RainboomHungerLevel) {
            sendExhaustedMessage(player);
            return;
        }
        synchronized (Settings.HasteLock) {
            FlightBean flightBean = new FlightBean(Settings.RainboomDuration, Settings.RainboomPower);
            Settings.HasteMap.put(player, flightBean);
            if (!player.hasPermission(Settings.BypassRestrictions)) {
                player.setFoodLevel(foodLevel - Settings.RainboomHunger);
                FoodLevelChangeEvent event = new FoodLevelChangeEvent(player, player.getFoodLevel());
                Bukkit.getServer().getPluginManager().callEvent(event);
            }
        }
    }

    private void DoHaste(Player player) {
        int foodLevel = player.getFoodLevel();
        if (foodLevel < Settings.HasteHungerLevel) {
            sendExhaustedMessage(player);
            return;
        }
        synchronized (Settings.HasteLock) {
            FlightBean flightBean = new FlightBean(Settings.HasteDuration, Settings.HastePower);
            Settings.HasteMap.put(player, flightBean);
            if (!player.hasPermission(Settings.BypassRestrictions)) {
                player.setFoodLevel(foodLevel - Settings.HasteHunger);
                FoodLevelChangeEvent event = new FoodLevelChangeEvent(player, player.getFoodLevel());
                Bukkit.getServer().getPluginManager().callEvent(event);
            }
        }
    }

    private void sendExhaustedMessage(Player player) {
        String message = (PIntro + ChatColor.RED) +
                "Вы слишком голодны для ускорения!";
        player.sendMessage(message);
    }
}
