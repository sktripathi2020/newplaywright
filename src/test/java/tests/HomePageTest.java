package tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import factory.PlaywrightFactory;


public class HomePageTest  {

	
	PlaywrightFactory playwrightFactory;
	Page page;
	protected Properties prop;

	@BeforeTest
	public void setup() throws IOException {
	    playwrightFactory = new PlaywrightFactory();
	    prop  = playwrightFactory.initProperties();
	    page = playwrightFactory.initBrowser(prop,false);
	    page.waitForLoadState(LoadState.LOAD);
	 }
	 @AfterTest(alwaysRun = true)
	 public void tearDown(){
	  	if(null !=page && null !=page.context() && null !=page.context().browser()) {
	   		page.context().browser().close();
	   	}
	 }
	    
	@Test
	public void homePageTitleTest() {
		System.out.println("TEST Starteddddddddd");
		assertTrue(page.locator("//img[@alt='Google']").isVisible());
		System.out.println("TEST PASSEDDDDDDDDDDDD");
	}
}