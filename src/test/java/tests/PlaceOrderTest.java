package tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.options.LoadState;

import pages.PlaceOrderPage;

public class PlaceOrderTest extends Session {

	private static Logger logger = LoggerFactory.getLogger(PlaceOrderTest.class);
	protected PlaceOrderPage placeOrderPage;

	@BeforeTest
	public void setup() throws IOException {
		page.context().browser()
				.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("auth.json")));

		System.out.println("session loaded");
		page.waitForLoadState(LoadState.LOAD);
		placeOrderPage = new PlaceOrderPage(page);
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {
		if (null != page && null != page.context() && null != page.context().browser()) {
			page.context().browser().close();
		}
	}

	@Test(priority = 1)
	public void doAddToCart() throws InterruptedException {
		boolean itemAdded = false;
		placeOrderPage.clickOnBierNav();
		
		if(placeOrderPage.getTotalProductOnPLP() > 0) { // Total results check 
			String fisrtProduct = placeOrderPage.findFirstProductXpath();
			placeOrderPage.doAddToCart(fisrtProduct);
			Thread.sleep(5000); // Do not remove 
		}
		int itemsInCart = placeOrderPage.getCartItems();
		if(itemsInCart>0) {
			logger.info("Items are added to cart Qty: " + itemsInCart);
			itemAdded = true;
		}else {
			logger.info("Items not added to Cart:");
		}
		
		assertTrue(itemAdded);
	}
	@Test(priority = 2)
	public void placeOrder() throws MalformedURLException {

		//Fetch  the cart items
		int itemsInCart = placeOrderPage.getCartItems();
		
		if(itemsInCart > 0) { //Checking cart should have more than one items in cart
			placeOrderPage.clickOnCartIcon();
			if(placeOrderPage.navigateCartPageUrl()) {  // check if navigate to cart URL
				
				placeOrderPage.clickContinueCheckout();
				
				if(placeOrderPage.navigateTODeliveryPage()) {
					placeOrderPage.clickConfirmButton();
					
					if(placeOrderPage.navigateOrderConfirmatonPage()) {
						placeOrderPage.clickOK();
						assertTrue(placeOrderPage.navigateToHomePage());
					}
				}
			}else {
				throw new MalformedURLException("Unable to navigate on cart page");
			}
		}else {
			throw new ArrayIndexOutOfBoundsException(" Cart should have at least one product");
		}
		
	}

}