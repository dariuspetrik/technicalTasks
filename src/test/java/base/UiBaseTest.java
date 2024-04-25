package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.InventoryPage;
import pages.LoginPage;
import utils.Constants;

public class UiBaseTest extends CommonBaseTest {

  protected Page page;
  protected Browser browser;
  protected BrowserContext browserContext;

  protected LoginPage loginPage;
  protected InventoryPage inventoryPage;

  private Browser getBrowser(){
    switch (Constants.BROWSER) {
      case "chrome":
        return playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
      case "firefox":
        return playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
      default:
        throw new IllegalArgumentException("Unsupported browser type: " + Constants.BROWSER);
    }
  }

  public void initializePageObjects() {
    loginPage = new LoginPage(page);
    inventoryPage = new InventoryPage(page);
  }
  @BeforeMethod
  public void beforeMethodSetup() {
    super.beforeMethodSetup();
    browserContext = getBrowser().newContext();
    page = browserContext.newPage();
    initializePageObjects();
  }

  @AfterMethod
  public void afterMethod() {
    if(page!=null){
      page.close();
    }

    if(browserContext!=null){
      browserContext.close();
    }

    if (browser != null) {
      browser.close();
    }
  }

}
