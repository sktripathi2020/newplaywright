package pages.headerlinks;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

import base.Constants;

public class FavoritesNavPage {
	private static Logger logger = LoggerFactory.getLogger(FavoritesNavPage.class);

	Page page;

	public FavoritesNavPage(Page page) {
		this.page = page;
		page.locator(Constants.AcceptCookieStr).click();
		page.locator(Constants.TermAndConditionStr).click();
	}

	public boolean isUserLoggedIn() {
		
		if (page.isVisible(Constants.UserProfile)) {
			return true;
		}
		return false;
	}
	
	public boolean doLogin(String userEmail, String userPassword) {
		page.click(Constants.HEADER_LOGIn_BUTTON);
		page.getByPlaceholder("Gebruikersnaam (géén e-mailadres)").fill(userEmail);
		page.getByPlaceholder("Wachtwoord").fill(userPassword);

		page.click(Constants.LoginBtn);

		if (page.isVisible(Constants.UserProfile)) {
			// set the login session in page
			logger.info("User Logged in Sucessfully - Products::NAv");
			page.context().storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("auth.json")));
			return true;
		}
		return false;
	}

	/*
	 * public boolean clickFavoritesNav() {
	 * 
	 * }
	 */
}
