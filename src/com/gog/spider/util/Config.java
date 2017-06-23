package com.gog.spider.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class Config {
    private static Map<String, Properties> configs = new HashMap<String, Properties>();
    private static final String DEFAULT_CONFIG_NAME = "system.properties";

    public Config(String configName) {
//        String filename = "resources/sytem.properties";
        Properties prop = new Properties();
        try {
            prop.load(Config.class.getClassLoader().getResourceAsStream(configName));

            //读取属性文件a.properties
            InputStream in = new BufferedInputStream(new FileInputStream("a.properties"));
            prop.load(in);     ///加载属性列表
            Iterator<String> it = prop.stringPropertyNames().iterator();

            Properties props = new Properties();
            String h = props.getProperty("v");

            while (it.hasNext()) {
                String key = it.next();
                System.out.println(key + ":" + prop.getProperty(key));
            }
            in.close();

            ///保存属性到b.properties文件
            FileOutputStream outputFile = new FileOutputStream("b.properties", true);//true表示追加打开
            prop.setProperty("phone", "10086");
            prop.store(outputFile, "The New properties file");
            outputFile.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Properties getConfig(String configName) {
        if (configName.isEmpty()) {
            configName = DEFAULT_CONFIG_NAME;
        }

        if (configs.containsKey(configName) && configs.get(configName) != null) {
            return configs.get(configName);
        }

        return loadConfig(configName);
    }

    private static Properties loadConfig(String configName) {
        Properties prop = null;
        try {
            prop = new Properties();
            prop.load(Config.class.getClassLoader().getResourceAsStream(configName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }

    private String getValue(String key) {
        // TODO
        return "";
    }

    public String getStringValue(String key) {
        // TODO
        return "";
    }
}
