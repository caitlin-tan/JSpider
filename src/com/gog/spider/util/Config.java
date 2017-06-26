package com.gog.spider.util;

import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Config {
    private static Map<String, ResourceBundle> configs = new HashMap<String, ResourceBundle>();
    private static final String DEFAULT_CONFIG_NAME = "system";

    private static ResourceBundle getConfig(String configName) {
        if (configName.isEmpty()) {
            configName = DEFAULT_CONFIG_NAME;
        }

        if (configs.containsKey(configName) && configs.get(configName) != null) {
            return configs.get(configName);
        }

        ResourceBundle config = loadConfig(configName);
        configs.put(configName, config);
        return config;
    }

    private static ResourceBundle loadConfig(String configName) {
        ResourceBundle config = null;
        try {
            config = ResourceBundle.getBundle(configName);
        } catch (MissingResourceException e) {
            e.printStackTrace();
        }

        return config;
    }

    public static String getStringByKey(String configName, String key) {
        ResourceBundle config = getConfig(configName);

        if (config == null) {
            return null;
        }

        return config.getString(key);
    }

    public static int getIntByKey(String configName, String key) {
        String value = getStringByKey(configName, key);
        int intValue = 0;

        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return intValue;
    }

    // TODO: 根据不同的环境，加载不同的配置
}
