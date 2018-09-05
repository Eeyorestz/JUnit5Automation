package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
  
  Properties prop;
  private void Config () {
     prop = new Properties();
    ClassLoader loader = Thread.currentThread().getContextClassLoader();           
    InputStream stream = loader.getResourceAsStream("config/application.properties");
    try {
      prop.load(stream);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    }
    public String getConfigProperty(String propertyName) { 
      Config ();
      String property = prop.getProperty(propertyName);
      return property;
  }

}
