
package net.inkyquill.equestria.flight;

import net.inkyquill.equestria.flight.commands.FlightAdminCommand;
import net.inkyquill.equestria.flight.commands.FlightHasteCommand;
import net.inkyquill.equestria.flight.listeners.MoveListener;
import net.inkyquill.equestria.flight.properties.Settings;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PegasusAbilities
        extends JavaPlugin {
    public void onEnable() {
        Settings.log = getLogger();
        Settings.plugin = this;
        Settings.loadSettings();

        PluginManager manager = Bukkit.getServer().getPluginManager();

        MoveListener moveListener = new MoveListener();
        manager.registerEvents(moveListener, Settings.plugin);

        this.getCommand("flightadmin").setExecutor(new FlightAdminCommand());
        FlightHasteCommand fc = new FlightHasteCommand();
        this.getCommand("haste").setExecutor(fc);
        this.getCommand("rainboom").setExecutor(fc);
    }

    public void onDisable() {
        Settings.saveSettings();
    }

}

