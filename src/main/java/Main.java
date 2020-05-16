import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            driver = new ChromeDriver();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            driver.get("https://rozetka.com.ua/");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='header-phones__button']")));
            String headerPhonesButtonText = driver.findElement(By.xpath("//*[@class='header-phones__button']")).getText();
            System.out.println(headerPhonesButtonText.replaceAll("[^\\d.]", ""));
        } catch (Exception e) {
            System.err.println("Error open site : " + e.getMessage());
        }
        driver.quit();
    }
}
