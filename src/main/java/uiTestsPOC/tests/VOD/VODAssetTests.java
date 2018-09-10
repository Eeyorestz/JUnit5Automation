package uiTestsPOC.tests.VOD;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.BrowserExtensions;
import utils.CommonMethods;
import utils.Config;


public class VODAssetTests extends BrowserExtensions {

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
 
  @Tag("develop")
  @Test
  public void V01crateVODAsset() {

    // Navigation to the page
    commonHelperMethods.leftPanelNavigation("VOD");
    commonHelperMethods.PopUpMenuSelecting("VOD Assets");
    commonHelperMethods.ToolbarButtonClick("Create VOD Assets");
    
    // To be uncomented
    //commonHelperMethods.fillRowInfo("Housenumber", "TestHousenumber");
   // commonHelperMethods.fillRowInfo("Name", "TestAssetName");
    //commonHelperMethods.fillRowInfo("Description", "TestAssetDescription");
    //commonHelperMethods.openDropdown("Content Type");  
    //commonHelperMethods.selectAnELementFromDropdown("CLIP");
    
    commonHelperMethods.openDropdown("TV Series"); 
    commonHelperMethods.searchForItem("Yordan");
    commonHelperMethods.selectAnELementFromDropdown("Yordan Test Series");
    
    commonHelperMethods.openDropdown("TV Season"); 
    commonHelperMethods.selectAnELementFromDropdown("Yordan Test Season 1");
    // Save info
    // commonHelperMethods.ToolbarButtonClick("Save");

    // Verify saved item by given property
    // commonHelperMethods.verifyCreatedItem("id");

    driver.close();
  }

  @Tag("regression")
  @Test
  public void P02editProduct() {

    // Navigation to the page
    commonHelperMethods.leftPanelNavigation("Products");
    commonHelperMethods.PopUpMenuSelecting("Products");

    // Prepare for edit
    commonHelperMethods.toolbarSearch("TestName");
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
  public void P03deleteProduct() {

    // Navigation to the page
    commonHelperMethods.leftPanelNavigation("Products");
    commonHelperMethods.PopUpMenuSelecting("Products");
    // Search for item
    commonHelperMethods.toolbarSearch("TestName");
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
