package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CommonMethods extends BrowserExtensions {

  CommonWebElements elements = new CommonWebElements();

  // Navigation methods
  public void login(String username, String password) {
    driver.findElement(By.xpath(".//*[@name='username']")).sendKeys(username);
    driver.findElement(By.xpath(".//*[@name='password']")).sendKeys(password);
    driver.findElement(By.xpath(".//button")).click();
  }

  public void leftPanelNavigation(String panelName) {
    driver.findElement(By.xpath("//*[contains(text(),'" + panelName + "')]/../..")).click();
  }

  public void PopUpMenuSelecting(String panelName) {
    WebElement element = driver.findElement(By.xpath(".//button[contains(text(),'" + panelName + "')]"));
    waitToBeClickable(element);
    element.click();
  }

  public void ToolbarButtonClick(String buttonName) {
    WebElement buttoAt = driver.findElement(By.xpath(" //div[@id='cdk-describedby-message-container']/*[normalize-space(text()) = '" + buttonName + "']"));
    String buttonAttribute = buttoAt.getAttribute("id").toString();
    WebElement button = driver.findElement(By.xpath("//*[@aria-describedby='" + buttonAttribute + "']"));
    waitToBeClickable(button);
    button.click();
    waitForPageLoaded();
  }

  // Edit methods
  public void selectPropertyForSearching(String propertyName) {
    WebElement openSearchDropdownButton = driver.findElement(By.xpath("(//*[contains(@class, 'mat-expansion-indicator ng')])[1]"));
    waitToBeClickable(openSearchDropdownButton);
    openSearchDropdownButton.click();
    ToolbarButtonClick(propertyName);
  }

  public void toolbarSearch(String valueForSearch) {
    WebElement searchField = driver.findElement(By.xpath("//input[@placeholder]"));
    waitToBeClickable(searchField);
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    searchField.sendKeys(valueForSearch);
  }

  public void fillRowInfo(String elementName, String data) {
    WebElement element = elements.rowForInput(elementName, getElementIndex(elementName));
    waitToBeClickable(element);
    element.click();

    // Check if the field is readonly or not by the class attribute
    if (!element.getAttribute("class").contains("htDimmed")) {
      element = driver.findElement(By.xpath("(//textarea)[1]"));
      element.sendKeys(data + Keys.ENTER);
    }
  }
  
  public void searchForItem(String searchValue) {  
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    WebElement element =  driver.findElement(elements.searchInput());
   
    waitToBeClickable(element); 
    element.sendKeys(searchValue);  
  // waitForElements(elements.dropdownElements(),5);
    try {
      Thread.sleep(600);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  

  // For single selection
  public void selectAnELementFromDropdown(String values) { 
    
    try {
      Thread.sleep(200);
    } catch (Exception e) {
    }
    List<WebElement> list = driver.findElements(elements.dropdownElements());
    for (int i = 0; i < list.size(); i++) {
      String eleText = list.get(i).getText();
      if (eleText.equals(values)) {
        waitToBeClickable(list.get(i));
        list.get(i).click();
        break;
      }
    }
  }

  // For multi selection
  public void selectAnELementFromDropdown(List<String> values) {
    
    List<WebElement> list = driver.findElements(elements.dropdownElements());

    for (int t = 0; t < values.size(); t++) {
      for (int i = 0; i < list.size(); i++) {
        String eleText = list.get(i).getText();
        if (eleText.equals(values.get(t))) {
          waitToBeClickable(list.get(i));
          list.get(i).click();
          list = driver.findElements(elements.dropdownElements());
          break;
        }
      }
    }
  }
  public void openDropdown(String rowName) {
    WebElement parentRow = elements.rowForInput(rowName, getElementIndex(rowName));
    waitToBeClickable(parentRow);
    parentRow.click();
    WebElement arrow = parentRow.findElement(elements.dropdownArrow());
    waitToBeClickable(arrow);
    parentRow.findElement(elements.dropdownArrow()).click();
  }

  // Verify methods
  public void verifyCreatedItem(String property) {
    String value = elements.rowForInput(property, getElementIndex(property)).getText();
    Assert.assertTrue(!value.equals(""));
  }

  public void verifyEditedItem() {
    String updateOnDate = elements.rowForInput("Updated On", getElementIndex("Updated On")).getText();
    String updatedBy = elements.rowForInput("Updated By", getElementIndex("Updated By")).getText();
    updateOnDate = updateOnDate.substring(0, updateOnDate.length() - 3);
    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime());
    Assert.assertTrue(updateOnDate.equals(timeStamp));
    Config config = new Config();
    String username = config.getConfigProperty("mdw.user");
    Assert.assertTrue(updatedBy.equals(username));
  }

  public void confirmDelete(String yesOrNo) {
    WebElement yesNoButton = driver.findElement(By.xpath(".//*[@class='mat-button-wrapper' and contains(text(),'" + yesOrNo + "')]/.."));
    waitToBeClickable(yesNoButton);
    yesNoButton.click();
  }

  public void verifyIsDelete(Integer before, Integer after, String propertyBefore, String propertyAfter) {
    Assert.assertTrue(before > after);
    Assert.assertTrue(!propertyBefore.equals(propertyAfter));
  }

  // Helper string and integer methods

  public String getGridPropety(String property) {
    String propertyText = "";
    try {
      propertyText = elements.gridFirstRow(property, getElementIndex(property)).getText();
    } catch (Exception e) {

    }
    return propertyText;
  }

  public Integer numberOfGridRows() {

    List<WebElement> elements = null;
    Integer size = 0;

    try {
      Thread.sleep(1000);
      elements = driver.findElements(By.xpath("//div[@class='ht_master handsontable']//tbody/tr"));
    } catch (Exception e) {

    }
    if (!elements.contains(null)) {
      size = elements.size();
    }
    return size;
  }

  private Integer getElementIndex(String elementName) {

    Integer elementIndex = 0;
    List<WebElement> elements = driver.findElements(By.xpath("//div[@class='ht_master handsontable']//thead//span"));
    for (int i = 0; i < elements.size(); i++) {
      if (elements.get(i).getText().equals(elementName)) {
        elementIndex = i;
        break;
      }
    }
    return elementIndex;
  }

}
