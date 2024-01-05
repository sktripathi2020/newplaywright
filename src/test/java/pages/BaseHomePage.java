package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BaseHomePage {

	 Page page;
     private String acceptCookieStr = "//*[@id=\"onetrust-accept-btn-handler\"]";
     private String termAndConditionStr = "//*[@id=\"sfbhoreca-popup\"]/div/div/div[1]/button/span";
     
     public BaseHomePage(Page page){
         this.page = page;
         Locator acceptCookie = page.locator(acceptCookieStr);
         acceptCookie.click();
         Locator acceptTermsAndCondition = page.locator(termAndConditionStr);
         acceptTermsAndCondition.click();
         
     }

}
