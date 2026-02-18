package by.tms.socialprojectc38onl.util;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {

    private final static Properties properties = new Properties();

    static {
        try (InputStream is = ApplicationProperties.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (is != null) {
                properties.load(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
