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
import pages.FooterPage;

@Test
public class FooterPageTest {
	
	PlaywrightFactory playwrightFactory;
	Page page;
	protected Properties prop;
	protected FooterPage footerPage;
	
	@BeforeTest
	public void setup() throws IOException, InterruptedException {
	    playwrightFactory = new PlaywrightFactory();
	    prop  = playwrightFactory.initProperties();
	    page = playwrightFactory.initBrowser(prop,false);
	    footerPage = new FooterPage(page);
	    page.waitForLoadState(LoadState.LOAD);
	    page.waitForTimeout(5000);
	 }
	 @AfterTest(alwaysRun = true)
	 public void tearDown(){
	  	if(null !=page && null !=page.context() && null !=page.context().browser()) {
	   		page.context().browser().close();
	   	}
	 }

	 @Test(priority = 1)
	 public void isFooterAvailable() {
		 assertTrue(footerPage.isFooterAvailable());
	 }
	 
	 @Test(priority = 2)
	 public void isMakeAnAppointmentAvailable() {
		 assertTrue(footerPage.isMakeAnAppointmentAvailable());
	 }
	 
	 @Test(priority = 3)
	 public void isTandCVisible() {
		 assertTrue(footerPage.isTandCVisible());
	 }
	 
	 @Test(priority = 4)
	 public void isPrivacyAndCookiesVisible() {
		 assertTrue(footerPage.isPrivacyAndCookiesVisible());
	 }
	 
	 @Test(priority = 5)
	 public void isSecurityVisible() {
		 assertTrue(footerPage.isSecurityVisible());
	 }
	 
	 @Test(priority = 6)
	 public void isContactVisible() {
		 assertTrue(footerPage.isContactVisible());
	 }
	 
	 @Test(priority = 7)
	 public void isVacanciesVisible() {
		 assertTrue(footerPage.isVacanciesVisible());
	 }
	 
	 @Test(priority = 8)
	 public void clickAndSubmitMakeAnAppointmentAvailable() throws InterruptedException {
		 assertTrue(footerPage.clickAndSubmitMakeAnAppointmentAvailable());
	 }
	 
	 @Test(priority = 9)
	 public void clickPrivacyAndCookies() throws InterruptedException {
		 assertTrue(footerPage.clickPrivacyAndCookies());
	 }
	 
	 @Test(priority = 10)
	 public void clickTAndC() throws InterruptedException {
		 assertTrue(footerPage.clickTAndC());
	 }
	 
	 @Test(priority = 11)
	 public void clickContact() throws InterruptedException {
		 assertTrue(footerPage.clickContact());
	 }
	 
	 @Test(priority = 12)
	 public void clickSecurity() throws InterruptedException {
		 assertTrue(footerPage.clickSecurity());
	 }
	 
	 @Test(priority = 13)
	 public void clickVACATURES() throws InterruptedException {
		 assertTrue(footerPage.clickVACATURES());
	 }
}
