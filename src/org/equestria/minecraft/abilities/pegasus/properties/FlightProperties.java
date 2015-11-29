/*
 * Decompiled with CFR 0_110.
 */
package org.equestria.minecraft.abilities.pegasus.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlightProperties {
    private static final Logger log = Logger.getLogger("FlightProperties");
    private static Properties properties = new Properties();
    private static final String FILE_NAME = "FlightProperties.properties";
    public static final String HASTE_DURATION_PROPERTY_NAME = "HasteDuration";
    public static final String HASTE_POWER_PROPERTY_NAME = "HastePower";
    public static final String HASTE_EXHAUST_PROPERTY_NAME = "HasteExhaustLevel";
    public static final String HASTE_EXHAUST_DEC_PROPERTY_NAME = "HasteExhaustDecrement";
    public static final String RAINBOOM_DURATION_PROPERTY_NAME = "RainboomDuration";
    public static final String RAINBOOM_POWER_PROPERTY_NAME = "RainboomPower";
    public static final String RAINBOOM_EXHAUST_PROPERTY_NAME = "RainboomExhaustLevel";
    public static final String RAINBOOM_EXHAUST_DEC_PROPERTY_NAME = "RainboomExhaustDecrement";

    public static void initProperties() {
        FlightProperties.initDefaultProperties();
        File file = new File("FlightProperties.properties");
        if (file.exists()) {
            FlightProperties.load();
        } else {
            FlightProperties.save();
        }
    }

    private static void initDefaultProperties() {
        if (!FlightProperties.keyExists("HasteDuration")) {
            FlightProperties.setString("HasteDuration", "100");
        }
        if (!FlightProperties.keyExists("HastePower")) {
            FlightProperties.setString("HastePower", "2");
        }
        if (!FlightProperties.keyExists("HasteExhaustDecrement")) {
            FlightProperties.setString("HasteExhaustDecrement", "2");
        }
        if (!FlightProperties.keyExists("HasteExhaustLevel")) {
            FlightProperties.setString("HasteExhaustLevel", "14");
        }
        if (!FlightProperties.keyExists("RainboomDuration")) {
            FlightProperties.setString("RainboomDuration", "100");
        }
        if (!FlightProperties.keyExists("RainboomPower")) {
            FlightProperties.setString("RainboomPower", "4");
        }
        if (!FlightProperties.keyExists("RainboomExhaustLevel")) {
            FlightProperties.setString("RainboomExhaustLevel", "14");
        }
        if (!FlightProperties.keyExists("RainboomExhaustDecrement")) {
            FlightProperties.setString("RainboomExhaustDecrement", "6");
        }
    }

    private static void load() {
        try {
            properties.load(new FileInputStream("FlightProperties.properties"));
        }
        catch (IOException ex) {
            log.log(Level.SEVERE, "Unable to load " + "FlightProperties.properties", ex);
        }
    }

    private static void save() {
        try {
            properties.store(new FileOutputStream("FlightProperties.properties"), "Flight config");
        }
        catch (IOException ex) {
            log.log(Level.SEVERE, "Unable to save " + "FlightProperties.properties", ex);
        }
    }

    public static boolean keyExists(String key) {
        return properties.containsKey(key);
    }

    public static String getString(String key) {
        if (FlightProperties.keyExists(key)) {
            return properties.getProperty(key);
        }
        return "";
    }

    private static void setString(String key, String value) {
        properties.setProperty(key, value);
    }

    public static int getInt(String key) {
        if (FlightProperties.keyExists(key)) {
            return Integer.parseInt(properties.getProperty(key));
        }
        return 0;
    }

    private static void setInt(String key, int value) {
        properties.setProperty(key, String.valueOf(value));
    }

    public static double getDouble(String key) {
        if (FlightProperties.keyExists(key)) {
            return Double.parseDouble(properties.getProperty(key));
        }
        return 0.0;
    }

    private void setDouble(String key, double value) {
        properties.setProperty(key, String.valueOf(value));
    }

    public static long getLong(String key) {
        if (FlightProperties.keyExists(key)) {
            return Long.parseLong(properties.getProperty(key));
        }
        return 0;
    }

    private void setLong(String key, long value) {
        properties.setProperty(key, String.valueOf(value));
    }

    public static boolean getBoolean(String key) {
        if (FlightProperties.keyExists(key)) {
            return Boolean.parseBoolean(properties.getProperty(key));
        }
        return false;
    }

    private static void setBoolean(String key, boolean value) {
        properties.setProperty(key, String.valueOf(value));
    }
}

