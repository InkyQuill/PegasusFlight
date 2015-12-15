/*
 * Decompiled with CFR 0_110.
 */
package net.inkyquill.equestria.flight.properties;

import net.inkyquill.equestria.flight.PegasusAbilities;
import net.inkyquill.equestria.flight.math.Region;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.permissions.Permission;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Settings {


    public static Material wand;
    public static Logger log;
    public static PegasusAbilities plugin;
    public static Map<String, Region> Regions;
    //perms:
    public static Permission FlightEnabled;
    public static Permission BypassRestrictions;
    public static Permission FlightAdmin;
    //settings:
    private static int HasteDuration;
    private static int HastePower;
    private static int HasteHunger;
    private static int HasteHungerLevel;
    private static int RainboomDuration;
    private static int RainboomPower;
    private static int RainboomHunger;
    private static int RainboomHungerLevel;

    static {
        FlightEnabled = new Permission("flight.enabled");
        BypassRestrictions = new Permission("flight.bypass");
        FlightAdmin = new Permission("flight.admin");
        wand = Material.FEATHER;
        Regions = new HashMap<String, Region>();
    }

    public static void loadSettings() {
        FileConfiguration config = plugin.getConfig();
        HasteDuration = config.getInt("haste.duration", 100);
        HastePower = config.getInt("haste.power", 2);
        HasteHunger = config.getInt("haste.hunger", 2);
        HasteHungerLevel = config.getInt("haste.hungerlevel", 14);

        RainboomDuration = config.getInt("rainboom.duration", 100);
        RainboomPower = config.getInt("rainboom.power", 4);
        RainboomHunger = config.getInt("rainboom.hunger", 6);
        RainboomHungerLevel = config.getInt("rainboom.hungerlevel", 14);

        Regions.clear();
        Map<String, Object> RegionsList = config.getConfigurationSection("regions").getValues(false);
        for (String key : RegionsList.keySet()) {
            String val = (String) (RegionsList.get(key));
            Region reg = Region.fromString(val);
            if (reg == null) continue;
            Regions.put(key, reg);
        }
    }

    public static void saveSettings() {
        FileConfiguration config = plugin.getConfig();
        config.set("haste.duration", HasteDuration);
        config.set("haste.power", HastePower);
        config.set("haste.hunger", HasteHunger);
        config.set("haste.hungerlevel", HasteHungerLevel);

        config.set("rainboom.duration", RainboomDuration);
        config.set("rainboom.power", RainboomPower);
        config.set("rainboom.hunger", RainboomHunger);
        config.set("rainboom.hungerlevel", RainboomHungerLevel);

        Map<String, String> RegionsList = new HashMap<String, String>();
        for (String key : Regions.keySet()) {
            RegionsList.put(key, Regions.get(key).toString());
        }
        config.createSection("regions", RegionsList);
        plugin.saveConfig();
    }
}
