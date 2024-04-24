package base;

import com.microsoft.playwright.Page;

public class BasePage {

  protected Page page;

  public BasePage(Page page) {
    this.page = page;
  }

  public void click(String locator) {
    page.click(locator);
  }

  public void sendText(String locator, String text) {
    page.fill(locator, "");
    page.fill(locator, text);
  }

  public String getElementText(String locator) {
    return page.locator(locator).innerText();
  }

  public String getCurrentURL(){
    return page.url();
  }

  public void waitForUrl(String url){
    page.waitForURL(url);
  }
}
