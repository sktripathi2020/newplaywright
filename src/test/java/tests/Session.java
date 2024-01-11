package tests;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import base.Constants;
import factory.PlaywrightFactory;

public class Session {
	PlaywrightFactory playwrightFactory;
	Page page;
	protected Properties prop;

	@BeforeSuite
	public void captureSession() throws IOException {

		playwrightFactory = new PlaywrightFactory();
		prop = playwrightFactory.initProperties();
		page = playwrightFactory.initBrowser(prop, false);

		Locator acceptCookie = page.locator(Constants.AcceptCookieStr);
		acceptCookie.click();
		Locator acceptTermsAndCondition = page.locator(Constants.TermAndConditionStr);
		acceptTermsAndCondition.click();

		page.locator("//a[contains(.,'Inloggen')]").click();
		page.getByPlaceholder("Gebruikersnaam (géén e-mailadres)").fill(prop.getProperty(Constants.USER_EMAIL));
		page.getByPlaceholder("Wachtwoord").fill(prop.getProperty(Constants.USER_PASSWORD));

		page.click(Constants.LoginBtn);

		if (page.isVisible(Constants.UserProfile)) {
			System.out.println("User Session Saved::");
			// set the login session in page
			page.context().storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("auth.json")));
		}

	}

	/*
	 * @BeforeMethod public void loadSession() throws IOException {
	 * 
	 * 
	 * playwrightFactory = new PlaywrightFactory(); prop =
	 * playwrightFactory.initProperties(); page =
	 * playwrightFactory.initBrowser(prop, false);
	 * 
	 * Locator acceptCookie = page.locator(Constants.AcceptCookieStr);
	 * acceptCookie.click(); Locator acceptTermsAndCondition =
	 * page.locator(Constants.TermAndConditionStr); acceptTermsAndCondition.click();
	 * 
	 * 
	 * page.context().browser() .newContext(new
	 * Browser.NewContextOptions().setStorageStatePath(Paths.get("auth.json")));
	 * 
	 * System.out.println("session loaded");
	 * 
	 * }
	 */

}
