package uiTestsPOC.SBTests;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.openqa.selenium.By;

import utils.PageBase;

public class APIElementsVerify extends PageBase {

 
  @Test
  public void CarouselVerification() {
    String baseURL = "http://qa.tvplayhome.lt:18081/sb/public/dashboard/type/PUBLIC_MOBILE?country=LV";
    driver.navigate().to(baseURL);

    List<String> dashboardElements = GetDetailsURL();
    List<String> carouselElements = new ArrayList<String>();
    List<String> contentElements = new ArrayList<String>();
    for (int i = 0; i < dashboardElements.size(); i++) {
      NavigateToUrl(dashboardElements.get(i));
      carouselElements = GetDetailsURL();

      for (int j = 0; j < carouselElements.size(); j++) {
        NavigateToUrl(carouselElements.get(j));
        contentElements = GetDetailsURL();
        for (int t = 0; t < contentElements.size(); t++) {
          NavigateToUrl(contentElements.get(t));
        }
      }
    }
  }
  private void NavigateToUrl(String suffixUrl) {
    String baseUrl = "http://qa.tvplayhome.lt:18081/sb/public";
    driver.navigate().to(baseUrl+suffixUrl);
  }
  private static final Pattern TAG_REGEX = Pattern.compile("\\/sb\\/public(.*?)\"");
  private List<String> GetDetailsURL() {
    String completeText = driver.findElement(By.xpath("//pre")).getText();
    final List<String> tagValues = new ArrayList<String>();
    final Matcher matcher = TAG_REGEX.matcher(completeText);
    while (matcher.find()) {
        tagValues.add(matcher.group(1));
    }
    return tagValues;
  }
}
