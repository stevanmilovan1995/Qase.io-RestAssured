package io.qase.automation.config;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static ConfigManager instance;
    private final Properties properties;

    private ConfigManager() {
        properties = new Properties();
        loadConfig();
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    private void loadConfig() {
        try {
            String configPath = "src/main/resources/config.properties";
            properties.load(new FileInputStream(configPath));
        } catch (IOException e) {
            throw new RuntimeException("Cannot load configuration. Check if config.properties file exists.", e);
        }
    }

    public String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Key '" + key + "' not found in configuration!");
        }
        return value;
    }
}
