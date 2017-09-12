package by.issoft.careers.appManager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class ApplicationManager {

    private final Properties properties;
    private NavigationHelper navigationHelper;
    private CareersHelper careersHelper;
    protected WebDriver driver;
    protected String baseUrl;
    protected StringBuffer verificationErrors = new StringBuffer();
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init()  throws IOException{
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

     if("".equals(properties.getProperty("selenium.server"))) {
        if (browser.equals(BrowserType.FIREFOX)) {
            driver = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
        } else if(browser.equals(BrowserType.CHROME)) {
            driver = new ChromeDriver();
        } else if(browser.equals(BrowserType.IE)) {
            driver = new InternetExplorerDriver();
        } else if(browser.equals(BrowserType.EDGE)) {
            driver = new EdgeDriver();}
        } else {
         DesiredCapabilities capabilities = new DesiredCapabilities();
         capabilities.setBrowserName(browser);
         driver = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
     }

        driver.get(properties.getProperty("web.baseUrl"));
        navigationHelper = new NavigationHelper(driver);
        careersHelper = new CareersHelper(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void stop() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
          fail(verificationErrorString);
        }
    }

    public NavigationHelper goTo() {  return navigationHelper; }

    public CareersHelper careers() {
        return careersHelper;
    }
}
