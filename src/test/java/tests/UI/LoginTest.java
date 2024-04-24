package tests.UI;

import base.UiBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import testData.DataProvider;
import utils.Constants;


public class LoginTest extends UiBaseTest {

  @Test
  public void standardUserLogin() {
    page.navigate(Constants.BASEURL);
    loginPage.enterCredentialsAndLogin(Constants.STANDARD_USER_USERNAME, Constants.PASSWORD);
    Assert.assertTrue(inventoryPage.getCurrentURL().contains("/inventory.html"));
  }

  @Test(dataProviderClass = DataProvider.class, dataProvider = "emptyCredentials")
  public void invalidLogin(String username, String password, String errorMessage) {
    page.navigate(Constants.BASEURL);
    loginPage.enterCredentialsAndLogin(username, password);
    Assert.assertEquals(loginPage.getInputFieldsErrorMessage(), errorMessage);
  }

  @Test
  public void lockedOutUserLogin() {
    page.navigate(Constants.BASEURL);
    loginPage.enterCredentialsAndLogin(Constants.LOCKED_OUT_USER_USERNAME, Constants.PASSWORD);
    Assert.assertEquals(loginPage.getInputFieldsErrorMessage(), Constants.LOCKED_OUT_USER_ERROR);
  }

  @Test
  public void performanceGlitchUserLogin() {
    page.navigate(Constants.BASEURL);
    loginPage.enterUserName(Constants.PERFORMANCE_GLITCH_USER_USERNAME);
    loginPage.enterPassword(Constants.PASSWORD);
    long startTime = System.currentTimeMillis();
    loginPage.clickLoginBtn();
    inventoryPage.waitForUrl(Constants.BASEURL+"/inventory.html");
    long endTime = System.currentTimeMillis();
    long pageLoadTime = endTime - startTime;
    Assert.assertTrue(pageLoadTime < Constants.MAX_PAGE_LOAD_TIMEOUT, "Page load took too long - " + pageLoadTime + "ms");}
}
