package tests.headerliks;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import base.Constants;
import factory.PlaywrightFactory;
import pages.SearchProductPage;
import pages.headerlinks.FavoritesNavPage;
import pages.headerlinks.ProductsNavPage;

public class FavoritesNavTest {
	private static Logger logger = LoggerFactory.getLogger(FavoritesNavTest.class);

	protected FavoritesNavPage favoritesNavPage;
	protected Page page;
	protected SearchProductPage searchProduct;
	PlaywrightFactory playwrightFactory;
	protected Properties prop;

	@BeforeTest
	public void setup() throws IOException {
		playwrightFactory = new PlaywrightFactory();
		prop = playwrightFactory.initProperties();
		page = playwrightFactory.initBrowser(prop, false);
		page.waitForLoadState(LoadState.LOAD);

		favoritesNavPage = new FavoritesNavPage(page);
		page.waitForLoadState(LoadState.LOAD);

	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {
		if (null != page && null != page.context() && null != page.context().browser()) {
			page.context().browser().close();
		}
	}

	@Test(priority = 0)
	public void doLogin() {
		assertTrue(favoritesNavPage.doLogin(Constants.USER_EMAIL, Constants.USER_PASSWORD));
	}

	//@Test(priority = 1)
	public void clickFavoritesNav() {
		if (!favoritesNavPage.isUserLoggedIn()) {
			favoritesNavPage.doLogin(Constants.USER_EMAIL, Constants.USER_PASSWORD);
		}
		//favoritesNavPage.clickFavoritesNav();
	}
}
