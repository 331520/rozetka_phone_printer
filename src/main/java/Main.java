import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            String expectedBorderColor =  "rgb(248, 65, 71)";
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            driver = new ChromeDriver();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            driver.get("http://rozetka.com.ua/");

            //войти в личный кабинет
            wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@class='header-topline__user-link link-dashed']")))).click();

            // - нажать "Зарегистрироваться"
            wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@class='auth-modal__register-link']")))).click();

            // - Оставить пустыми «Ваше имя», «Эл. Почта или номер телефона», «Придумайте Пароль»
            wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@class='form__row js-name']")))).click();
            WebElement nameField = driver.findElement(By.xpath("//*[@formcontrolname='name']"));
            nameField.sendKeys("Саня");

            wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@class='form__row js-contact']")))).click();
            wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@class='form__row js-new_password']")))).click();
            // - нажать "Зарегистрироваться"

            //Нажать войти
            wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@class='button button_size_large button_color_green auth-modal__submit']")))).click();

            System.out.println(wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@formcontrolname='name']")))).getCssValue("border-color")); //formcontrolname="name"
            System.out.println(wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@formcontrolname='username']")))).getCssValue("border-color")); //formcontrolname="username"
            System.out.println(wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@formcontrolname='password']")))).getCssValue("border-color")); //formcontrolname="name"

        } catch (Exception e) {
            System.err.println("Error open site : " + e.getMessage());
        }
    }

    public void customAwaiting(int second){
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
