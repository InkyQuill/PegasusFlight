/*
 * Decompiled with CFR 0_110.
 */
package net.inkyquill.equestria.flight.properties;

import net.inkyquill.equestria.flight.PegasusAbilities;
import net.inkyquill.equestria.flight.math.FlightBean;
import net.inkyquill.equestria.flight.math.Region;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Settings {


    public static final Object HasteLock;
    public static Material wand;
    public static Logger log;
    public static PegasusAbilities plugin;
    public static Map<String, Region> Regions;
    //perms:
    public static Permission FlightEnabled;
    public static Permission BypassRestrictions;
    public static Permission FlightAdmin;
    public static Permission NoFallDamage;
    //settings:
    public static int HasteDuration;
    public static int HastePower;
    public static int HasteHunger;
    public static int HasteHungerLevel;
    public static int RainboomDuration;
    public static int RainboomPower;
    public static int RainboomHunger;
    public static int RainboomHungerLevel;
    public static Map<Player, FlightBean> HasteMap;

    static {
        FlightEnabled = new Permission("flight.enabled");
        BypassRestrictions = new Permission("flight.bypass");
        FlightAdmin = new Permission("flight.admin");
        NoFallDamage = new Permission("flight.nodamage");
        wand = Material.FEATHER;
        Regions = new HashMap<String, Region>();
        HasteMap = new HashMap<Player, FlightBean>();
        HasteLock = new Object();
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

