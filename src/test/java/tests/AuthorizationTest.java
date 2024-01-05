package tests;


import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.PlaywrightException;

public class AuthorizationTest {
	
	public static void main(String[] args) throws InterruptedException {
		Map<String,String> env = new HashMap<String, String>();
		//env.put("PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD","1");
		//env.put("PLAYWRIGHT_BROWSERS_PATH", "USERPROFILE\\pw-browsers");
		
				try {
					Playwright playwright = Playwright.create(new Playwright.CreateOptions().setEnv(env));
						 Browser browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false));
						 
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
