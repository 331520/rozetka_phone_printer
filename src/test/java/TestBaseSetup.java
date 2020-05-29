import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utils.Screenshot;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBaseSetup {
    WebDriver driver;
    Screenshot screenshot;


    @Parameters({ "browser" })
    @BeforeMethod
    public void beforeMethod(String browser){
        System.out.println("browser : " + browser);

        if (browser.equals("firefox")){
            FirefoxOptions options = new FirefoxOptions();
            //System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
            options.addArguments("--disable-notifications");
            //options.addArguments("--window-size=1300,1080");
            options.addArguments("--headless");
            driver = new FirefoxDriver(options);
            try {
                driver = new RemoteWebDriver(new URL("http://ec2-18-191-164-149.us-east-2.compute.amazonaws.com:4444/wd/hub"), options);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            ChromeOptions options = new ChromeOptions();
            //System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            options.addArguments("--disable-notifications");
            options.addArguments("--window-size=1300,1080");
            options.addArguments("--headless");
            //driver = new ChromeDriver(options);
            try {
                driver = new RemoteWebDriver(new URL("http://ec2-18-191-164-149.us-east-2.compute.amazonaws.com:4444/wd/hub"), options);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        screenshot = new Screenshot(driver);
    }

    @AfterMethod

    public void afterMethod(ITestResult result) {
        screenshot.getScreenshot(result);
        driver.quit();
    }

}
