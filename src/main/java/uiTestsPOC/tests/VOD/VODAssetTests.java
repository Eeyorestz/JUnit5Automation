package uiTestsPOC.tests.VOD;

import java.util.Arrays;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import utils.BrowserExtensions;
import utils.CommonMethods;
import utils.Config;


@Tag("Regression")
public class VODAssetTests  extends BrowserExtensions {

  CommonMethods commonHelperMethods = new CommonMethods();
  Config config = new Config();

  private String url = config.getConfigProperty("int.server.url");
  private String username = config.getConfigProperty("mdw.user");
  private String password = config.getConfigProperty("mdw.password");

  @BeforeEach
  public void initialize() {
    driver.get(url);
    commonHelperMethods.login(username, password);
  }

  // Product Tests
  
  @Test
  void V01crateVODAsset() {

    // Navigation to the page
    commonHelperMethods.leftPanelNavigation("VOD");
    commonHelperMethods.PopUpMenuSelecting("VOD Assets");
    commonHelperMethods.ToolbarButtonClick("Create VOD Assets");

    // Fill mandatory fields Plus try to fill the readonly fields
   // commonHelperMethods.selectAnELementFromDropdown("Channel Packages", channelPackages);
    commonHelperMethods.fillRowInfo("Housenumber", "TestHousenumber");
    commonHelperMethods.fillRowInfo("Name", "TestAssetName");
    commonHelperMethods.fillRowInfo("Description", "TestAssetDescription");
    //commonHelperMethods.fillRowInfo("Episode Number", "32");
    commonHelperMethods.selectAnELementFromDropdown("Content Type", "CLIP");
    // Save info
   // commonHelperMethods.ToolbarButtonClick("Save");

    // Verify saved item by given property
   // commonHelperMethods.verifyCreatedItem("id");

    driver.close();
  }
  @Tag("Regression")
 @Test
  void P02editProduct() {

  // Navigation to the page
  commonHelperMethods.leftPanelNavigation("Products");
  commonHelperMethods.PopUpMenuSelecting("Products");

  // Prepare for edit
  commonHelperMethods.enterValueInSearchField("TestName");
  commonHelperMethods.ToolbarButtonClick("Search");

  commonHelperMethods.ToolbarButtonClick("Edit Product");

  commonHelperMethods.fillRowInfo("Name", "TestName Edit");
  commonHelperMethods.fillRowInfo("Description", "TestDescription Edit");

  // Save info
  commonHelperMethods.ToolbarButtonClick("Save");
  commonHelperMethods.verifyEditedItem();

  driver.close();
}
  
  @Test
  
  void P03deleteProduct() {

    // Navigation to the page
    commonHelperMethods.leftPanelNavigation("Products");
    commonHelperMethods.PopUpMenuSelecting("Products");
    // Search for item
    commonHelperMethods.enterValueInSearchField("TestName");
    commonHelperMethods.ToolbarButtonClick("Search");
    // Pre-delete
    Integer before = commonHelperMethods.numberOfGridRows();
    String idBefore = commonHelperMethods.getGridPropety("Id");
    commonHelperMethods.ToolbarButtonClick("Delete Product");
    commonHelperMethods.confirmDelete("Yes");
    // Pos-delete
    Integer after = commonHelperMethods.numberOfGridRows();
    String idAfter = commonHelperMethods.getGridPropety("Id");
    commonHelperMethods.verifyIsDelete(before, after, idBefore, idAfter);

    driver.close();
  }

}
