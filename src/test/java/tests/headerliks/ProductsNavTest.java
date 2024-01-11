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
import pages.headerlinks.ProductsNavPage;

public class ProductsNavTest{

	private static Logger logger = LoggerFactory.getLogger(ProductsNavTest.class);
	
	protected ProductsNavPage productsPage;
	protected Page page;
	protected SearchProductPage searchProduct;
	PlaywrightFactory playwrightFactory;
	protected Properties prop;
	
	
	@BeforeTest
	public void setup() throws IOException {
		playwrightFactory = new PlaywrightFactory();
        prop  = playwrightFactory.initProperties();
        page = playwrightFactory.initBrowser(prop,null);
        page.waitForLoadState(LoadState.LOAD);
		
		productsPage = new ProductsNavPage(page);
		page.waitForLoadState(LoadState.LOAD);
		
	}
	
	  @AfterTest(alwaysRun = true)
	    public void tearDown(){
	    	if(null !=page && null !=page.context() && null !=page.context().browser()) {
	    		page.context().browser().close();
	    	}
	    }
	  
	@Test(priority = 0)
	public void doLogin() {
		assertTrue(productsPage.doLogin(prop.getProperty(Constants.USER_EMAIL), prop.getProperty(Constants.USER_PASSWORD)));
	}
	
	@Test(priority = 1)
	public void clickBeerNav() {
		if(!productsPage.isUserLoggedIn()) {
			productsPage.doLogin(Constants.USER_EMAIL,Constants.USER_PASSWORD);
		}
		productsPage.clickOnBierNav();
		assertTrue(productsPage.getTotalProductOnPLP());
	}
	
	@Test(priority = 2)
	public void clickWineNav() {
		if(!productsPage.isUserLoggedIn()) {
			productsPage.doLogin(Constants.USER_EMAIL,Constants.USER_PASSWORD);
		}
		productsPage.clickOnWineNav();
		assertTrue(productsPage.getTotalProductOnPLP());
	}
	
	@Test(priority = 3)
	public void clickOnDistilledNav() {
		if(!productsPage.isUserLoggedIn()) {
			productsPage.doLogin(Constants.USER_EMAIL,Constants.USER_PASSWORD);
		}
		productsPage.clickOnDistilledNav();
		assertTrue(productsPage.getTotalProductOnPLP());
	}
	
	@Test(priority = 4)
	public void clickOnMixedDrinkNav() {
		if(!productsPage.isUserLoggedIn()) {
			productsPage.doLogin(Constants.USER_EMAIL,Constants.USER_PASSWORD);
		}
		productsPage.clickOnMixedDrinkNav();
		assertTrue(productsPage.getTotalProductOnPLP());
	}
	
	@Test(priority = 5)
	public void clickOnSoftDrinkNav() {
		if(!productsPage.isUserLoggedIn()) {
			productsPage.doLogin(Constants.USER_EMAIL,Constants.USER_PASSWORD);
		}
		productsPage.clickOnSoftDrinkNav();
		assertTrue(productsPage.getTotalProductOnPLP());
	}
	
	@Test(priority = 6)
	public void clickOnFruitJuiceNav() {
		if(!productsPage.isUserLoggedIn()) {
			productsPage.doLogin(Constants.USER_EMAIL,Constants.USER_PASSWORD);
		}
		productsPage.clickOnFruitJuiceNav();
		assertTrue(productsPage.getTotalProductOnPLP());
	}
	
	
	@Test(priority = 7)
	public void clickOnSportsEnergyDrinksNav() {
		if(!productsPage.isUserLoggedIn()) {
			productsPage.doLogin(Constants.USER_EMAIL,Constants.USER_PASSWORD);
		}
		productsPage.clickOnSportsEnergyDrinksNav();
		assertTrue(productsPage.getTotalProductOnPLP());
	}
	
	@Test(priority = 8)
	public void clickOnWaterNav() {
		if(!productsPage.isUserLoggedIn()) {
			productsPage.doLogin(Constants.USER_EMAIL,Constants.USER_PASSWORD);
		}
		productsPage.clickOnWaterNav();
		assertTrue(productsPage.getTotalProductOnPLP());
	}
	
	@Test(priority = 9)
	public void clickOnDairyNav() {
		if(!productsPage.isUserLoggedIn()) {
			productsPage.doLogin(Constants.USER_EMAIL,Constants.USER_PASSWORD);
		}
		productsPage.clickOnDairyNav();
		assertTrue(productsPage.getTotalProductOnPLP());
	}
	
	@Test(priority = 10)
	public void clickOnSyrupNav() {
		if(!productsPage.isUserLoggedIn()) {
			productsPage.doLogin(Constants.USER_EMAIL,Constants.USER_PASSWORD);
		}
		productsPage.clickOnSyrupNav();
		assertTrue(productsPage.getTotalProductOnPLP());
	}
	
	@Test(priority = 11)
	public void clickOnNonDrinksNav() {
		if(!productsPage.isUserLoggedIn()) {
			productsPage.doLogin(Constants.USER_EMAIL,Constants.USER_PASSWORD);
		}
		productsPage.clickOnNonDrinksNav();
		assertTrue(productsPage.getTotalProductOnPLP());
	}
}
