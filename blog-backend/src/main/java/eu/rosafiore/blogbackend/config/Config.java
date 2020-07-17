package eu.rosafiore.blogbackend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Component
@ConfigurationProperties(prefix = "config")
@Validated
public class Config {
    public static String USERNAME;
    public static String PASSWORD;
    private static final String PROPERTIES_LOCATION = System.getProperty("user.home") +
            "/RosaFiore/rosafiore.properties";

    public static void getConfig() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PROPERTIES_LOCATION));
            USERNAME = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
