package tests;

import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import base.Constants;
import factory.PlaywrightFactory;
import pages.HomePage;
import pages.LoginPage;

public class HomePageTest  {

	
	PlaywrightFactory playwrightFactory;
	Page page;
	protected Properties prop;
	protected HomePage homePage;
	protected LoginPage loginPage;

	@BeforeTest
	public void setup() throws IOException {
	    playwrightFactory = new PlaywrightFactory();
	    prop  = playwrightFactory.initProperties();
	    page = playwrightFactory.initBrowser(prop,false);
	    page.waitForLoadState(LoadState.LOAD);
	    homePage = new HomePage(page);
	 }
	 @AfterTest(alwaysRun = true)
	 public void tearDown(){
	  	if(null !=page && null !=page.context() && null !=page.context().browser()) {
	   		page.context().browser().close();
	   	}
	 }
	    
	@Test
	public void homePageTitleTest() {
		String actualTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actualTitle, Constants.HOME_PAGE_TITLE);
	}

	@Test
	public void homeLogoTest() {
		boolean  logoVisible = homePage.getHomePageLogo();
		Assert.assertTrue(logoVisible);
	}
}