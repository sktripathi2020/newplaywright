package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import base.Constants;

public class SearchProductPage {// BaseHomePage{
	protected Page page;
	public SearchProductPage(Page page) {

		this.page = page;
		Locator acceptCookie = page.locator(Constants.AcceptCookieStr);
        acceptCookie.click();
        Locator acceptTermsAndCondition = page.locator(Constants.TermAndConditionStr);
        acceptTermsAndCondition.click();
        
        page.locator("//a[contains(.,'Inloggen')]").click();
        page.getByPlaceholder("Gebruikersnaam (géén e-mailadres)").fill(Constants.USER_EMAIL);
        page.getByPlaceholder("Wachtwoord").fill(Constants.USER_PASSWORD);
        page.click(Constants.LoginBtn);
	}

	public boolean login() {
		return page.isVisible(Constants.UserProfile);
		
	}

	public boolean doSearch() {
		String breadCrumbText = "//a[@class='breadcrumb-item-4 breadcrumb-last']";//'"+Constants.SearchTerm+"'";
		page.getByPlaceholder("Zoeken naar...").first().fill(Constants.SearchTerm);
        page.locator(Constants.SearchButton).click();
        
		
        if(page.isVisible(breadCrumbText)) {
        	return true;
        }
        
        return false;
	}
	
	public boolean searchPageUrl() {
		if(page.url().contains("/search.html?input=")) {
		
			return true;
		}
		return false;
		}
	
}