package base;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class CommonBaseTest {

  protected Playwright playwright;
  protected APIRequestContext apiRequestContext;
  protected SoftAssert softAssert;

  @BeforeMethod
  public void beforeMethodSetup() {
    softAssert = new SoftAssert();
    playwright = Playwright.create();
  }

  @AfterMethod
  public void afterMethod(){
    if (playwright != null) {
      playwright.close();
    }
  }
}
