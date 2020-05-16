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
            driver.get("http://rozetka.com.ua/");

            //войти в личный кабинет
            wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@class='header-topline__user-link link-dashed']")))).click();

            // - нажать "Зарегистрироваться"
            wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@class='auth-modal__register-link']")))).click();

            // - Оставить пустыми «Ваше имя», «Эл. Почта или номер телефона», «Придумайте Пароль»

            //Нажать войти
            wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@class='button button_size_large button_color_green auth-modal__submit']")))).click();
        } catch (Exception e) {
            System.err.println("Error open site : " + e.getMessage());
        }
        driver.quit();
    }
}
