package base;

import org.testng.annotations.BeforeMethod;
import requests.UserRequest;

public class ApiBaseTest extends CommonBaseTest {

  protected UserRequest userRequest;
  public void initializeTestComponents(){
    userRequest = new UserRequest(apiRequestContext);
  }
  @BeforeMethod
  public void beforeMethodSetup() {
    super.beforeMethodSetup();
    apiRequestContext = playwright.request().newContext();
    initializeTestComponents();
  }

}
