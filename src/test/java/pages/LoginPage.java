package pages;

import java.nio.file.Paths;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {
     Page page;
     private String acceptCookieStr = "//*[@id=\"onetrust-accept-btn-handler\"]";
     private String termAndConditionStr = "//*[@id=\"sfbhoreca-popup\"]/div/div/div[1]/button/span";

     public LoginPage(Page page){
         this.page = page;
         
         Locator acceptCookie = page.locator(acceptCookieStr);
         acceptCookie.click();
         Locator acceptTermsAndCondition = page.locator(termAndConditionStr);
         acceptTermsAndCondition.click();
         
     }
     private String emailPlaceholder = "Gebruikersnaam (géén e-mailadres)";
     private String passwordPlaceHolder = "Wachtwoord";
     private String loginBtn = "//*[@id=\"login-form\"]/input[5]";
     private String forgotPassword = "//a[@class='btForgotPass']";
     private String logoutBtn = "//a[normalize-space()='Uitloggen']";
     private String userProfile =  "//div[@class='top-nav-container']//i[@class='fa fa-user']";
     ////div[@class='loginerror']
     //Swinkels Family Brewers - Horeca
     
     public boolean isLoginPage() {
    	 page.locator("//a[contains(.,'Inloggen')]").click();
    	 if(page.isVisible(loginBtn)) {
    		 return true;
    	 }
    	 return false;
     }

     public String getLoginPageTitle(){
         return page.title();
     }
     
     public boolean isLoginButtonVisible() {
    	if(page.isVisible(loginBtn)) {
    		return true;
  		}
  		return false;
     }
     public boolean isUserProfileVisible(){
         return page.isVisible(userProfile);
     }
     
     public boolean doLogin(String userEmail,String userPassword){
    	// page.locator("//a[contains(.,'Inloggen')]").click();
         page.getByPlaceholder("Gebruikersnaam (géén e-mailadres)").fill(userEmail);
         page.getByPlaceholder("Wachtwoord").fill(userPassword);
    	 
         page.click(loginBtn);
         
         if(page.isVisible(userProfile)){
             System.out.println("User is logged in");
             //set the login session in page
             page.context().storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("auth.json")));
             return true;
         }
         return false;
     }
     
}
