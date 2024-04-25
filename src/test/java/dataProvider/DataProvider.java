package dataProvider;

import utils.Constants;

public class DataProvider {
  @org.testng.annotations.DataProvider(name = "emptyCredentials")
  public static Object[][] emptyCredentials() {
    return new Object[][]{
        {Constants.STANDARD_USER_USERNAME, "", Constants.EMPTY_PASSWORD_ERROR},
        {"", Constants.PASSWORD, Constants.EMPTY_USERNAME_ERROR}
    };
  }
  @org.testng.annotations.DataProvider(name = "userPage")
  public static Object[][] userPage() {
    return new Object[][]{
        {"page","2"},
    };
  }

  @org.testng.annotations.DataProvider(name = "user")
  public static Object[][] user() {
    return new Object[][]{
        {"morpheus","leader"},
        {"user1","QA"}
    };
  }
}
