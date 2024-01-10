/*
 * package base;
 * 
 * import com.microsoft.playwright.Page; import factory.PlaywrightFactory;
 * import org.testng.annotations.AfterTest; import
 * org.testng.annotations.BeforeTest; import pages.HomePage; import
 * pages.LoginPage;
 * 
 * import java.io.IOException; import java.util.Properties;
 * 
 * 
 * public class BaseTest { PlaywrightFactory playwrightFactory; Page page;
 * protected Properties prop; protected HomePage homePage; protected LoginPage
 * loginPage;
 * 
 * @BeforeTest public void setup() throws IOException { playwrightFactory = new
 * PlaywrightFactory(); prop = playwrightFactory.initProperties(); page =
 * playwrightFactory.initBrowser(prop,true); homePage = new HomePage(page); }
 * 
 * @AfterTest(alwaysRun = true) public void tearDown(){ if(null !=page && null
 * !=page.context() && null !=page.context().browser()) {
 * page.context().browser().close(); } } }
 * 
 */