/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.PluginCommand
 *  org.bukkit.plugin.java.JavaPlugin
 */
package org.equestria.minecraft.abilities.pegasus;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.equestria.minecraft.abilities.pegasus.flight.FlightAbility;

public class PegasusAbilities
extends JavaPlugin {
    public void onEnable() {
        FlightAbility flightAbility = new FlightAbility(this);
        this.getCommand("flight").setExecutor((CommandExecutor)flightAbility);
    }
}

