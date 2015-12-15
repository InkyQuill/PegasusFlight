/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.PluginCommand
 *  org.bukkit.plugin.java.JavaPlugin
 */
package net.inkyquill.equestria.flight;

import net.inkyquill.equestria.flight.properties.Settings;
import org.bukkit.plugin.java.JavaPlugin;
import net.inkyquill.equestria.flight.commands.FlightAbility;

public class PegasusAbilities
extends JavaPlugin {
    public void onEnable() {
        Settings.log = getLogger();
        Settings.plugin = this;
        Settings.loadSettings();

        FlightAbility flightAbility = new FlightAbility();
        this.getCommand("flight").setExecutor(flightAbility);
    }

    public void onDisable() {
        Settings.saveSettings();

    }
}

