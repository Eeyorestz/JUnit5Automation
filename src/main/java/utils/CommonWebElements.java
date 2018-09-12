package utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class CommonWebElements extends BrowserExtensions{

  public By dropdownArrow () {   
    return By.className("htAutocompleteArrow");
  }
  
  public By dropdownElements () {   
    return By.xpath(".//div[@class='ss-list']/div");
  }
   
  public WebElement gridFirstRow(String elementName, Integer elementIndex) {
    List<WebElement> elements;
    WebElement element = null;
    try {
      elements = driver.findElements(By.xpath("(//div[@class='ht_master handsontable']//tbody/tr)[1]/td"));
      element = elements.get(elementIndex);
    } catch (Exception e) {

    }
    return element;
  }
  
  public WebElement rowForInput(String elementName, Integer elementIndex) {
    List<WebElement> elements = driver.findElements(By.xpath("//div[@class='ht_master handsontable']//tbody//td"));
    WebElement element = elements.get(elementIndex);
    return element;
  }
  
  public By searchInput() {
    return By.xpath("//input[@type='search']");   
  }
  
  public WebElement day(String day) {
    return driver.findElement(By.xpath("//td[contains(@class,'calendar')]/div[normalize-space(text()) = '"+day+"']")) ;   
  }  

}
