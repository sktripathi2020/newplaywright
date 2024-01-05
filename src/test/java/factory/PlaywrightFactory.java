package factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

public class PlaywrightFactory {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties properties;
    private static ThreadLocal<Browser> threadLocalBrowser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> threadLocalContext = new ThreadLocal<>();
    private static ThreadLocal<Page> threadLocalPage = new ThreadLocal<>();
    private static ThreadLocal<Playwright> threadLocalPlaywright = new ThreadLocal<>();

    public static Playwright getPlayWright(){
        return threadLocalPlaywright.get();
    }
    public static Browser getBrowser(){
        return threadLocalBrowser.get();
    }
    public static BrowserContext getContext(){
        return threadLocalContext.get();
    }
    public static Page getPage(){
        return threadLocalPage.get();
    }

    public Page initBrowser(Properties properties,Boolean headless) {
//        playwright = Playwright.create();
    	if(null ==headless) {
    		headless = Boolean.valueOf(properties.getProperty("headless"));
    	}
          threadLocalPlaywright.set(Playwright.create());
      String browserName =  properties.getProperty("browser");

        switch (browserName.toLowerCase().trim()) {
            case "chromium":
//              browser =  playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                threadLocalBrowser.set(getPlayWright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless)));
                break;

            case "firefox":
//             browser =   playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                threadLocalBrowser.set(getPlayWright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless)));
                break;
            case "safari":
//             browser =   playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                threadLocalBrowser.set(getPlayWright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless)));
                break;
            case "chrome":
//             browser =   playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome")
//                        .setHeadless(false));
                threadLocalBrowser.set(getPlayWright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome")
                        .setHeadless(headless)));
                break;
            default:
                System.out.println("Please pass the right browser name");
                break;
        }
        threadLocalContext.set(getBrowser().newContext());
        threadLocalPage.set(getContext().newPage());
        getPage().navigate(properties.getProperty("url"));
//        browserContext = browser.newContext();
//        page = browserContext.newPage();
//        page.navigate(properties.getProperty("url"));
        return getPage();

    }
    public Properties initProperties() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//config.properties");
        properties = new Properties();
        properties.load(fileInputStream);
        return properties;
    }
    public static String takeScreenshot() {
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        //getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

        byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        String base64Path = Base64.getEncoder().encodeToString(buffer);

        return base64Path;
    }
}
