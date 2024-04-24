package testData;

import utils.Constants;

public class DataProvider {
  @org.testng.annotations.DataProvider(name = "emptyCredentials")
  public static Object[][] emptyCredentials() {
    return new Object[][]{
        {Constants.STANDARD_USER_USERNAME, "", Constants.EMPTY_PASSWORD_ERROR},
        {"", Constants.PASSWORD, Constants.EMPTY_USERNAME_ERROR}
    };
  }
}
