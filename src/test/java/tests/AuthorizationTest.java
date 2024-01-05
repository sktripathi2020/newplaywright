package tests;


import static org.testng.Assert.assertTrue;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.PlaywrightException;

public class AuthorizationTest {
	
	public static void main(String[] args) throws InterruptedException {
		
				try {
					Playwright playwright = Playwright.create();
						 Browser browser = playwright.webkit().launch(
				            		new LaunchOptions().setHeadless(false)
				            		);
						 
					Page page = browser.newPage();
		            page.navigate("https://www.google.com/");
		            
		            System.out.println("AuthorizationTest Starteddddddddd");
		    		assertTrue(page.locator("//img[@alt='Google']").isVisible());
		    		System.out.println("AuthorizationTest PASSEDDDDDDDDDDDD");
		    		
				}catch(PlaywrightException err) {
					err.printStackTrace();
				}
	            
	            
	    }
}
