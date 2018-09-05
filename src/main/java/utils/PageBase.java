package utils;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import com.paulhammant.ngwebdriver.NgWebDriver;

public abstract class PageBase {

  private NgWebDriver ngWebDriver;
  public static WebDriver driver;

  
  @BeforeEach
  public  void openBrowser() {
    Config config = new Config();

    String browser = config.getConfigProperty("browser");

    switch (browser) {
      case "ch":
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        String isHeadless = config.getConfigProperty("isHeadless").toUpperCase();
        if (isHeadless.equals("YES")) {
          chromeOptions.addArguments("--headless");
        }
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        break;
      case "ff":
      
        break;
      case "sf":
        
        break;

      default:
       
        break;
    }

  }

  // public NgWebDriver Protractor()
  // {
  // ngWebDriver = new NgWebDriver(driver);
  // return ngWebDriver;
  // }
}
