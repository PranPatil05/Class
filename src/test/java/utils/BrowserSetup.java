package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.fileHandlers.ExcelUtils;

public class BrowserSetup {
    public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    public static WebDriver driver;


    public static synchronized WebDriver getDriver() {
        return threadDriver.get();
    }

    public static WebDriver launchChromeBrowser() {
        TestBase.log("Launching Chrome Browser");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        WebDriverManager.chromedriver().setup();//bonigarcia dependancy for browser version changes.
        driver = new ChromeDriver(options);
        return driver;
    }
    public static WebDriver launchFirefoxBrowser() {
        TestBase.log("Launching Firefox Browser");
        DesiredCapabilities caps = new DesiredCapabilities();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-private");
        caps.setCapability("moz:firefoxOptions", options);

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(caps);
        return driver;
    }

    public WebDriver invokeWebDriver() {
        try {
            switch (ExcelUtils.readPropertyFromExcel("Configuration", "Browser").toUpperCase()) {
                case "CHROME": launchChromeBrowser();
                    break;

                case "FIREFOX": launchFirefoxBrowser();
                    break;

                default: TestBase.log("Launching Default Browser");
                    launchChromeBrowser();
            }

            threadDriver.set(driver);
            return getDriver();
        } catch (Exception e) {
            TestBase.log("Exception occurred.\n" + e.getStackTrace());
            return getDriver();
        }
    }
}

/*
Steps-
1.  Came from test Base
2.  ThreadLocal (java.lang) class uses for maintain HashMap as Key to smooth parallel execution when multile browser wimdows are open.
3.  create getDriver() method to use ThreadLocal method with reference threadDriverto get values.Therad safe i.e why write as synchronized.
4.  Dependacy bonigarcia add for webdriver
5.  public static WebDriver driver; craete variable -Write a mthod to launch browser for both chrome or Firefox

 */
