package pages.headerlinks;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

import base.Constants;

public class ProductsNavPage {

	private static Logger logger = LoggerFactory.getLogger(ProductsNavPage.class);

	Page page;

	public ProductsNavPage(Page page) {
		this.page = page;
		page.locator(Constants.AcceptCookieStr).click();
		page.locator(Constants.TermAndConditionStr).click();
	}

	public boolean isUserLoggedIn() {
		
		if (page.isVisible(Constants.UserProfile)) {
			return true;
		}
		return false;
	}
	
	public boolean doLogin(String userEmail, String userPassword) {
		page.click(Constants.HEADER_LOGIn_BUTTON);
		page.getByPlaceholder("Gebruikersnaam (géén e-mailadres)").fill(userEmail);
		page.getByPlaceholder("Wachtwoord").fill(userPassword);

		page.click(Constants.LoginBtn);

		if (page.isVisible(Constants.UserProfile)) {
			// set the login session in page
			logger.info("User Logged in Sucessfully - Products::NAv");
			page.context().storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("auth.json")));
			return true;
		}
		return false;
	}

	public boolean getTotalProductOnPLP() {
		int totalProduct =  Integer.valueOf(page.locator("//span[@class='filter-number']").last().innerText());
		return totalProduct>0?true:false;
	}

	public void clickOnBierNav() {
		page.locator("//div[@id='categories-collapse']//a[normalize-space()='Bier']").click();
	}
	
	
	public void clickOnWineNav() {
		page.locator("//div[@id='categories-collapse']//a[normalize-space()='Wijn']").click();
	}
	
	
	public void clickOnDistilledNav() {
		page.locator("//div[@id='categories-collapse']//a[normalize-space()='Gedistilleerd']").click();
	}
	
	
	
	public void clickOnMixedDrinkNav() {
		page.locator("//div[@id='categories-collapse']//a[normalize-space()='Mixdrank']").click();
	}
	
	
	public void clickOnSoftDrinkNav() {
		page.locator("//div[@id='categories-collapse']//a[normalize-space()='Frisdrank']").click();
	}
	
	
	public void clickOnFruitJuiceNav() {
		page.locator("//div[@id='categories-collapse']//a[normalize-space()='Vruchtensap']").click();
	}
	
	public void clickOnSportsEnergyDrinksNav() {
		page.locator("//div[@id='categories-collapse']//a[normalize-space()='Sport en Energie drank']").click();
	}
	
	
	public void clickOnWaterNav() {
		page.locator("//div[@id='categories-collapse']//a[normalize-space()='Water']").click();
	}
	
	
	public void clickOnDairyNav() {
		page.locator("//div[@id='categories-collapse']//a[normalize-space()='Zuivel']").click();
	}
	
	
	public void clickOnSyrupNav() {
		page.locator("//div[@id='categories-collapse']//a[normalize-space()='Siroop']").click();
	}
	
	public void clickOnNonDrinksNav() {
		page.locator("//div[@id='categories-collapse']//a[normalize-space()='Non Drinks']").click();
	}
	
	
}
