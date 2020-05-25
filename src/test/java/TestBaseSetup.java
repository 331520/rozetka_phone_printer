import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Screenshot;

public class TestBaseSetup {
    WebDriver driver;
    Screenshot screenshot;

    @BeforeMethod
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        //options.addArguments("--window-size=1300,1080");
        driver = new ChromeDriver();
        //driver.manage().window().maximize();
        screenshot = new Screenshot(driver);
    }

    @AfterMethod

    public void afterMethod(ITestResult result) {
        screenshot.getScreenshot(result);
        driver.quit();
    }

}
