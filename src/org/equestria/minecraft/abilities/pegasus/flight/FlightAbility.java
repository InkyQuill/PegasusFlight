/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Listener
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.PluginManager
 */
package org.equestria.minecraft.abilities.pegasus.flight;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.equestria.minecraft.abilities.pegasus.PegasusAbilities;
import org.equestria.minecraft.abilities.pegasus.flight.FlightHandler;
import org.equestria.minecraft.abilities.pegasus.flight.MoveListener;
import org.equestria.minecraft.abilities.pegasus.properties.FlightProperties;

public class FlightAbility
implements CommandExecutor {
    public static final Logger log = Logger.getLogger("FlightAbility");
    public static final String ABILITY_COMMAND = "flight";
    private PegasusAbilities plugin;

    public FlightAbility(PegasusAbilities pligin) {
        FlightProperties.initProperties();
        this.plugin = pligin;
        MoveListener moveListener = new MoveListener();
        Bukkit.getServer().getPluginManager().registerEvents((Listener)moveListener, (Plugin)this.plugin);
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] as) {
        if ("flight".equals(command.getName())) {
            return this.onFlightCommandExecute(commandSender, as);
        }
        return false;
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

