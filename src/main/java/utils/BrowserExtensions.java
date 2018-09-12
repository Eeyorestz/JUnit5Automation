package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.paulhammant.ngwebdriver.NgWebDriver;

public class BrowserExtensions extends PageBase {
  static void JSClick(WebElement element) {
    ExecuteScript("arguments[0].click();", element);
  }

  public void waitForPageLoaded() {
    ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver driver) {
        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
      }
    };
    try {
      Thread.sleep(1000);
      WebDriverWait wait = new WebDriverWait(driver, 30);
      wait.until(expectation);
    } catch (Throwable error) {
    }
  }

  public void waitToBeClickable(WebElement element) {
    WebDriverWait wait2 = new WebDriverWait(driver, 5);
    wait2.until(ExpectedConditions.elementToBeClickable(element));
    JavascriptExecutor jse2 = (JavascriptExecutor) driver;
    jse2.executeScript("arguments[0].scrollIntoView()", element);
  }

  public Integer waitForElements(By element, Integer seconds) {
    Integer timer = 0;
    Integer temp = 0;
    Integer numberOfElements = 0;
    while (timer < seconds && numberOfElements == 0) {
      {

        numberOfElements = driver.findElements(element).size();

        try {
          Thread.sleep(100);
        } catch (InterruptedException e2) {
        }

        temp++;
        if (temp % 10 == 0) {
          timer++;
        }
      }
    }
    return numberOfElements;

  }

  static Object ExecuteScript(String script, Object element) {
    return ((JavascriptExecutor) driver).executeScript(script, element);
  }
}
