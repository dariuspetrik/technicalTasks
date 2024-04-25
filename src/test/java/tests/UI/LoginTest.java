package tests.UI;

import base.UiBaseTest;
import dataProvider.DataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Constants;


public class LoginTest extends UiBaseTest {

  /**
   * This test verifies successful login for standard user
   * It is important to know that user can log in with correct credentials
   */
  @Test
  public void standardUserLogin() {
    page.navigate(Constants.BASEURL);
    loginPage.enterCredentialsAndLogin(Constants.STANDARD_USER_USERNAME, Constants.PASSWORD);
    Assert.assertTrue(inventoryPage.getCurrentURL().contains("/inventory.html"));
  }

  /**
   * This test verifies that user can not log in with empty credentials
   * It is important to know that system can handle wrong - empty credentials
   *
   */
  @Test(dataProviderClass = DataProvider.class, dataProvider = "emptyCredentials")
  public void invalidLogin(String username, String password, String errorMessage) {
    page.navigate(Constants.BASEURL);
    loginPage.enterCredentialsAndLogin(username, password);
    Assert.assertEquals(loginPage.getInputFieldsErrorMessage(), errorMessage);
  }

  /**
   * This test verifies that user can not use credentials for locked account
   * It is important to let the user know about the locked account
   **/
  @Test
  public void lockedOutUserLogin() {
    page.navigate(Constants.BASEURL);
    loginPage.enterCredentialsAndLogin(Constants.LOCKED_OUT_USER_USERNAME, Constants.PASSWORD);
    Assert.assertEquals(loginPage.getInputFieldsErrorMessage(), Constants.LOCKED_OUT_USER_ERROR);
  }

  /**
   * This test verifies how quickly user can log in
   * It is important to know the system performance (page loading)
   */
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
