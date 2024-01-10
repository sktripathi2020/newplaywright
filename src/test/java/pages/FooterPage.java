package pages;

import com.microsoft.playwright.Page;

import base.Constants;

public class FooterPage {
	protected Page page;

	private static final String TC = "//a[normalize-space()='Algemene Voorwaarden']";
	private static final String APPOINTMENT = "//a[@id='cta_button_home_footer-ctabutton']";
	private static final String PC = "//a[normalize-space()='Privacy & Cookies']";
	private static final String SECURITY= "//a[normalize-space()='Security']";
	private static final String CONATCT= "//a[normalize-space()='Contact']";
	private static final String VACATURES = "//a[normalize-space()='Vacatures']";
	
	public FooterPage(Page page) {

		this.page = page;
		page.locator(Constants.AcceptCookieStr).click();
        page.locator(Constants.TermAndConditionStr).click();
	}
	
	public boolean isFooterAvailable() {
		return page.locator("//Div[@class='section footer-section ']").isVisible()?true:false;
	}
	
	public boolean isMakeAnAppointmentAvailable() {
		return page.locator(APPOINTMENT).isVisible()?true:false;
	}
	
	public boolean isTandCVisible() {
		return page.locator(TC).isVisible()?true:false;
	}
	
	public boolean isPrivacyAndCookiesVisible() {
		return page.locator(PC).isVisible()?true:false;
	}
	
	
	public boolean isSecurityVisible() {
		return page.locator(SECURITY).isVisible()?true:false;
	}
	
	
	public boolean isContactVisible() {
		return page.locator(CONATCT).last().isVisible()?true:false;
	}
	
	public boolean isVacanciesVisible() {
		return page.locator(VACATURES).isVisible()?true:false;
	}
	
	public boolean clickAndSubmitMakeAnAppointmentAvailable() throws InterruptedException {
		boolean flag = false;
		String url = page.url();
		
		if(page.locator(APPOINTMENT).isVisible()) {
			page.locator(APPOINTMENT).click();
			
			if(page.url().contains("/afspraak-maken.html")){
				//submitMakeAnAppointmentAvailable(page,flag);
				flag = true;
			}
		}
		return flag;
	}

	
	public boolean clickPrivacyAndCookies() throws InterruptedException {
		boolean flag = false;
		if(page.locator(PC).isVisible()) {
			page.locator(PC).click();
			
			if(page.url().contains("/privacyencookies.html")){
				flag = true;
			}
		}
		return flag;
	}
	
	public boolean clickTAndC() throws InterruptedException {
		boolean flag = false;
		if(page.locator(TC).isVisible()) {
			page.locator(TC).click();
			
			if(page.url().contains("/algemenevoorwaarden.html")){
				flag = true;
			}
		}
		return flag;
	}
	
	public boolean clickContact() throws InterruptedException {
		boolean flag = false;
		if(page.locator(CONATCT).last().isVisible()) {
			page.locator(CONATCT).last().click();
			
			if(page.url().contains("/contact.html")){
				//flag = submitContactInfo(page);  // Waiting for reCaptcha Solutions
				flag = true;
			}
		}
		return flag;
	}
	
	
	private boolean submitContactInfo(Page page) throws InterruptedException {
		
		
		page.getByLabel("Naam zaak").fill("Test");
		page.getByLabel("Telefoon nr").fill("916666666666");
		page.getByLabel("Plaats").fill("Test");
		page.getByLabel("Bericht").fill("Test");
		page.getByLabel("E-mail").first().fill("test@gmail.com");
		page.waitForLoadState();
		page.locator("//input[@name='Submit']");
		System.out.println("page URL :: " + page.url());
		if(page.url().contains("/afspraakmakenbevestiging.html?status=200")) {
			return true;
		}
	 return false;
		
	}

	public boolean clickSecurity() throws InterruptedException {
		boolean flag = false;
		if(page.locator(SECURITY).isVisible()) {
			page.locator(SECURITY).click();
			
			if(page.url().contains("/security.html")){
				flag = true;
			}
		}
		return flag;
	}
	
	public boolean clickVACATURES() throws InterruptedException {
		boolean flag = false;
		if(page.locator(VACATURES).isVisible()) {
			
			
			page.waitForPopup(
					new Page.WaitForPopupOptions().setPredicate(p->p.context().pages().size()==2),
					()->{page.locator(VACATURES).click();
				});
			for(Page tab : page.context().pages()) {
				System.out.println(tab.url());
				if(tab.url().contains("/vacatures")){
					flag = true;
					break;
				}
			}
			
		}
		
		return flag;
	}
	private void submitMakeAnAppointmentAvailable(Page page,Boolean flag) {
		
		page.getByPlaceholder("Postcodecijfers (1234)").fill("1234");
		page.getByLabel("Naam:").fill("Test");
		page.getByLabel("E-mailadres:").fill("test@gmail.com");
		page.getByLabel("Telefoonnummer:").fill("916666666666");
		page.getByLabel("Plaats").fill("Test");
		page.getByLabel("Bericht").fill("Test Message");
		
		
		//input[@name='Submit']
		
	}
}
