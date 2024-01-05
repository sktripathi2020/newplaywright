package tests;

import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import base.Constants;
import factory.PlaywrightFactory;
import pages.HomePage;
import pages.LoginPage;

public class LoginPageTest {

	PlaywrightFactory playwrightFactory;
	Page page;
	protected Properties prop;
	protected LoginPage loginPage;

	@BeforeTest
	public void setup() throws IOException {
		playwrightFactory = new PlaywrightFactory();
		prop = playwrightFactory.initProperties();
		page = playwrightFactory.initBrowser(prop, false);
		page.waitForLoadState(LoadState.LOAD);
		loginPage = new LoginPage(page);
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {
		if (null != page && null != page.context() && null != page.context().browser()) {
			page.context().browser().close();
		}
	}

	@Test(priority = 1)
	public void loginPageNavigationTest() {
		Assert.assertTrue(loginPage.isLoginPage());
	}

	@Test(priority = 2)
	public void appLoginTest() {
		Assert.assertTrue(loginPage.doLogin(Constants.USER_EMAIL, Constants.USER_PASSWORD));
	}

	@Test(priority = 3)
	public void loggedInPageTitleTest() {
		String actLoginPageTitle = loginPage.getLoginPageTitle();
		System.out.println("page act title: " + actLoginPageTitle);
		Assert.assertEquals(actLoginPageTitle, Constants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 4)
	public void UserProfileVisiblTest() {
		Assert.assertTrue(loginPage.isUserProfileVisible());
	}

}