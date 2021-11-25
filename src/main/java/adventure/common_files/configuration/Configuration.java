package adventure.common_files.configuration;

import java.io.IOException;
import java.util.Properties;

public class Configuration {
  private static Properties properties = new Properties();

  static {
    try {
      properties.load(Configuration.class.getResourceAsStream("/application.properties"));
    } catch (
            IOException e) {
      e.printStackTrace();
    }
  }

  public static Properties getProperties() {
    return properties;
  }

  public static String getProperty(String value) {
    return properties.getProperty(value);
  }

}
