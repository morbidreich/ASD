package io.github.morbidreich.utils;

import java.io.*;
import java.util.Properties;

public class SettingsManager {
    private static final String SETTINGS_FILE = "src/main/resources/settings.properties";
    private Properties properties = null;

    private static SettingsManager instance;

    private SettingsManager() {
        loadSettings();
    }
    //singleton
    public static SettingsManager getInstance() {
        // this is not thread safe, in case of problems check
        // https://dzone.com/articles/singleton-in-java
        if (instance==null) {
            instance = new SettingsManager();
        }
        return instance;
    }

    private void loadSettings() {
        properties = new Properties();
        //try-with-resources ensures that input stream will be closed
        try(InputStream input = new FileInputStream(SETTINGS_FILE)) {
            properties.load(input);
        }
        catch (IOException e) {
            System.out.println("Error loading properties file, using default values");
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public void set(String key, String value) {
        try (OutputStream output = new FileOutputStream(SETTINGS_FILE)) {
            properties.setProperty(key, value);
            properties.store(output, null);
        }
        catch (IOException ex) {
            System.out.println("error");
        }
    }

    public enum VectorLength {
        ZERO (0),
        ONE (1),
        THREE (3),
        FIVE (5);

        private final int length;

        VectorLength(int length) {
            this.length = length;
        }

        public int getLength() {
            return this.length;
        }
    }

    public enum HistoryLength {
        SHORT (5),
        MEDIUM (8),
        LONG (12);

        private final int length;

        HistoryLength(int length) {
            this.length = length;
        }
        public int getLength() {return this.length;}
    }

    public static void main(String[] args) {
        String out = SettingsManager.getInstance().get("plot.brightness");
        System.out.println(out);
    }

}

