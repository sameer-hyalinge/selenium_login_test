package testngdemo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    // Static block runs only once when class is loaded
    static {
        try {
            // Load the config file
            FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
            prop = new Properties();
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    // Static method to access property values
    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}
