package base;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class CommonBaseTest {

  protected Playwright playwright;
  protected APIRequestContext apiRequestContext;

  @BeforeMethod
  public void beforeMethodSetup() {
    playwright = Playwright.create();
    apiRequestContext = playwright.request().newContext();
  }

  @AfterMethod
  public void afterMethod(){
    if (playwright != null) {
      playwright.close();
    }
  }
}
