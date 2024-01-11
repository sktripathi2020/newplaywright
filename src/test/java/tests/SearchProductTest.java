package tests;

import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import factory.PlaywrightFactory;
import pages.SearchProductPage;

public class SearchProductTest{

	protected SearchProductPage searchProduct;
	PlaywrightFactory playwrightFactory;
	Page page;
	protected Properties prop;

	@BeforeTest
	public void setup() throws IOException {
		playwrightFactory = new PlaywrightFactory(); 
		prop = playwrightFactory.initProperties(); 
		page = playwrightFactory.initBrowser(prop, false);
		page.waitForLoadState(LoadState.LOAD);
		searchProduct = new SearchProductPage(page,prop);
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {
		if (null != page && null != page.context() && null != page.context().browser()) {
			page.context().browser().close();
		}
	}
	
	
	@Test(priority = 1)
	public void LoginTest(){
		Assert.assertTrue(searchProduct.login());
	}
	
	@Test(priority = 2)
	public void doSearch(){
		Assert.assertTrue(searchProduct.doSearch());
	}
	
	@Test(priority = 3)
	public void searchPageUrlText(){
		Assert.assertTrue(searchProduct.searchPageUrl());
	}

}