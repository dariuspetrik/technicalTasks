package pages;

import base.BasePage;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

 private String usernameInput = "input[id='user-name']";
 private String passwordInput = "input[id='password']";
 private String loginBtn = "input[id='login-button']";
 private String errorMessage = "h3[data-test='error']";

  public LoginPage(Page page) {
    super(page);
  }

  public void enterUserName(String username) {
    sendText(usernameInput, username);
  }

  public void enterPassword(String password) {
    sendText(passwordInput, password);
  }

  public void clickLoginBtn() {
    click(loginBtn);
  }

  public void enterCredentialsAndLogin(String username, String password) {
    enterUserName(username);
    enterPassword(password);
    clickLoginBtn();
 }

 public String getInputFieldsErrorMessage() {
   return getElementText(errorMessage);
 }
}
