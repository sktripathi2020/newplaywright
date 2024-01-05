package pages;
import com.microsoft.playwright.*;
public class HomePage {

    Page page;

  
    private String acceptCookieStr = "//*[@id=\"onetrust-accept-btn-handler\"]";
    private String termAndConditionStr = "//*[@id=\"sfbhoreca-popup\"]/div/div/div[1]/button/span";

    private String search = "input[name='search']";
	private String searchIcon = "div#search button";
	private String searchPageHeader = "div#content h1";
	
	private String myAccountLink = "//i[@class='fa fa-user']";
	
 // 2. page constructor:
 	public HomePage(Page page) {
 		 this.page = page;
 		 Locator acceptCookie = page.locator(acceptCookieStr);
         acceptCookie.click();
         Locator acceptTermsAndCondition = page.locator(termAndConditionStr);
         acceptTermsAndCondition.click();
 	}

 	// 3. page actions/methods:
 	public String getHomePageTitle() {
 		String title =  page.title();
 		System.out.println("page title: " + title);
 		return title;
 	}

 	public boolean getHomePageLogo() {
 		return page.locator("//a[@class='logo']//img").isVisible();
 	}

 	public String doSearch(String productName) {
 		page.fill(search, productName);
 		page.click(searchIcon);
 		String header =  page.textContent(searchPageHeader);
 		System.out.println("search header: " + header);
 		return header;
 	}
 	
 	public LoginPage navigateToLoginPage() {
 		page.click(myAccountLink);
 		return new LoginPage(page);
 	}

 }