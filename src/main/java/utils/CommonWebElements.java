package utils;

import org.openqa.selenium.By;


public class CommonWebElements extends BrowserExtensions{

  public By dropdownArrow () {   
    return By.className("htAutocompleteArrow");
  }
  
  public By dropdownElements () {   
    return By.xpath(".//div[@class='ss-list']/div");
  }
}
