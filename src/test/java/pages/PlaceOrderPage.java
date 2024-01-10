package pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import base.Constants;
import tests.PlaceOrderTest;

public class PlaceOrderPage {// BaseHomePage{
	private static Logger logger = LoggerFactory.getLogger(PlaceOrderPage.class);
	protected Page page;

	public PlaceOrderPage(Page page) {

		this.page = page;
	}

	public boolean login() {
		return page.isVisible(Constants.UserProfile);

	}

	public boolean doSearch() {
		String breadCrumbText = "//a[@class='breadcrumb-item-4 breadcrumb-last']";// '"+Constants.SearchTerm+"'";
		page.getByPlaceholder("Zoeken naar...").first().fill(Constants.SearchTerm);
		page.locator(Constants.SearchButton).click();

		if (page.isVisible(breadCrumbText)) {
			return true;
		}

		return false;
	}

	public boolean navigateCartPageUrl() {
		if (page.url().contains("cart.html")) {

			return true;
		}
		return false;
	}

	public boolean navigateTODeliveryPage() {
		if (page.url().contains("delivery.html")) {

			return true;
		}
		return false;
	}

	public boolean navigateOrderConfirmatonPage() {
		if (page.url().contains("confirmation.html")) {

			return true;
		}
		return false;
	}
	
	public void clickOnBierNav() {
		page.locator("//div[@id='categories-collapse']//a[normalize-space()='Bier']").click();
	}
	
	public int getCartItems() {
		return Integer.valueOf(page.locator("//div[@class='top-nav-container']//a[@class='btShoppingCart']").last().innerText());
	}

	public int getTotalProductOnPLP() {
		
		return Integer.valueOf(page.locator("//span[@class='filter-number']").last().innerText());
		
	}

	public String findFirstProductXpath() {
		
		String productStr[] = page.locator("//p[@class='product-element-subtitle']").first().innerText().split(" ");
		StringBuilder sb = new StringBuilder();
		sb.append("//div[@id='product_sale_");
		sb.append(productStr[(productStr.length)-1]);
		sb.append("']//a[@aria-label='plus']");
		
		logger.info("First Product XPath :" + sb.toString());
		////div[@id='product_sale_104362']//a[@aria-label='plus']
		return sb.toString();
	}
	
	public void doAddToCart(String xPath) {
		page.locator(xPath).click();
	}
	
	public void clickOnCartIcon() {
		page.click("//div[@class='top-nav-container']//i[@class='fa fa-shopping-cart']");
	}
	
	public void clickContinueCheckout() {
		page.click("//a[@id='cta_button_cart_order_button']");
	}
	
	public void clickConfirmButton() {
		page.click("//a[@id='cta_button_delivery_confirm_order_button']");
		page.reload();
	}
	
	public void clickOK() {
		page.click("//a[@id='cta_button_confirmation_confirm_button']']");
	}
	
	public boolean  navigateToHomePage() {
		return page.url().contains("/home-logged-in.html")?true:false;
	}
}