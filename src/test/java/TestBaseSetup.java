import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utils.Screenshot;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBaseSetup {
    WebDriver driver;


    @Feature("Setup browser")
    @Parameters({"browser"})
    @BeforeMethod
    public void beforeMethod(String browser, ITestContext iTestContext) {
        System.out.println("browser : " + browser);
        FirefoxOptions optionsF = new FirefoxOptions();
        optionsF.addArguments("--disable-notifications");
        //optionsF.addArguments("--headless");

        ChromeOptions optionsC = new ChromeOptions();
        optionsC.addArguments("--disable-notifications");
        optionsC.addArguments("--window-size=1300,1080");
        //optionsC.addArguments("--headless");

        if (browser.equals("firefox")) {
            //System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
            //options.addArguments("--window-size=1300,1080");
            //options.addArguments("--headless");
            driver = new FirefoxDriver(optionsF);
            /*try {
                driver = new RemoteWebDriver(new URL("http://ec2-18-191-164-149.us-east-2.compute.amazonaws.com:4444/wd/hub"), options);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }*/
        } else {
            //System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            driver = new ChromeDriver(optionsC);
/*            try {
                driver = new RemoteWebDriver(new URL("http://ec2-18-191-164-149.us-east-2.compute.amazonaws.com:4444/wd/hub"), options);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }*/
        }
        iTestContext.setAttribute("driver", driver);
    }

    @AfterMethod

    public void afterMethod(ITestResult result) {
        driver.quit();
    }

}
