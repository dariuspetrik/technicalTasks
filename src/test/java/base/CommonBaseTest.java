package base;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import requests.UserRequest;

public class CommonBaseTest {

  protected Playwright playwright;
  protected APIRequestContext apiRequestContext;
  protected SoftAssert softAssert;

  protected UserRequest userRequest;

  public void initializeTestComponents(){
    userRequest = new UserRequest(apiRequestContext);
  }
  @BeforeMethod
  public void beforeMethodSetup() {
    softAssert = new SoftAssert();
    playwright = Playwright.create();
    apiRequestContext = playwright.request().newContext();
    initializeTestComponents();
  }

  @AfterMethod
  public void afterMethod(){
    if (playwright != null) {
      playwright.close();
    }
  }
}
