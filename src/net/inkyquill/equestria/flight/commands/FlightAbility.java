
package net.inkyquill.equestria.flight.commands;

import net.inkyquill.equestria.flight.handle.FlightHandler;
import net.inkyquill.equestria.flight.listeners.MoveListener;
import net.inkyquill.equestria.flight.properties.Settings;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.inkyquill.equestria.flight.PegasusAbilities;

public class FlightAbility
implements CommandExecutor {

    public FlightAbility() {
        MoveListener moveListener = new MoveListener();
        Bukkit.getServer().getPluginManager().registerEvents(moveListener, Settings.plugin);
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] as) {
        return "flight".equals(command.getName()) && this.onFlightCommandExecute(commandSender, as);
    }

    private boolean onFlightCommandExecute(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (args.length == 0) {
                FlightHandler.handleNoArgsCommand(player);
                return false;
            }
            if (args.length > 2) {
                FlightHandler.handleWrongArgsCommand(player);
                return false;
            }
            return FlightHandler.handleCommand(player, args[0]);
        }
        return false;
    }
}

