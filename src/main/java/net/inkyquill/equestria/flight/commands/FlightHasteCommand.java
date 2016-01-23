package net.inkyquill.equestria.flight.commands;

import net.inkyquill.equestria.flight.math.FlightBean;
import net.inkyquill.equestria.flight.properties.Settings;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * Created by obruchnikov_pa on 22.12.2015.
 */
public class FlightHasteCommand implements CommandExecutor {

    static String PIntro = ChatColor.GOLD + "[Flight/Haste] ";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof ConsoleCommandSender) {
            commandSender.sendMessage(PIntro + ChatColor.RED + "Silly console, you are not flying!");
            return true;
        }

        Player p = (Player) commandSender;
        if (!p.hasPermission(Settings.FlightEnabled)) {
            p.sendMessage(PIntro + ChatColor.RED + "Nope, you can't haste.");
            return true;
        }

        if (command.getName().toLowerCase() == "haste") {
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
            player.setFoodLevel(foodLevel - Settings.RainboomHunger);
            FoodLevelChangeEvent event = new FoodLevelChangeEvent(player, player.getFoodLevel());
            Bukkit.getServer().getPluginManager().callEvent(event);
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
            player.setFoodLevel(foodLevel - Settings.HasteHunger);
            FoodLevelChangeEvent event = new FoodLevelChangeEvent(player, player.getFoodLevel());
            Bukkit.getServer().getPluginManager().callEvent(event);
        }
    }

    private void sendExhaustedMessage(Player player) {
        StringBuilder message = new StringBuilder();
        message.append(PIntro + ChatColor.RED);
        message.append("Too exhausted to haste!");
        player.sendMessage(message.toString());
    }
}
