import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;


public class RozetkaTest {

    static WebDriver driver = null;

    @Test
    public static void negativeRegistrationAttemptAllFields() {

        try {
            String expectedBorderColor = "rgb(248, 65, 71)";
            String actualBorderColor = "";
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
            wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@class='form__row js-contact']")))).click();
            wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@class='form__row js-new_password']")))).click();
            // - нажать "Зарегистрироваться"

            //Нажать войти
            wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@class='button button_size_large button_color_green auth-modal__submit']")))).click();

            System.out.println(wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@formcontrolname='name']")))).getCssValue("border-color")); //formcontrolname="name"
            System.out.println(wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@formcontrolname='username']")))).getCssValue("border-color")); //formcontrolname="username"
            //comment out the line below to fail the test
            System.out.println(wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@formcontrolname='password']")))).getCssValue("border-color")); //formcontrolname="name"
            /*
            if (wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@formcontrolname='name']")))).getCssValue("border-color").toString().equals(expectedBorderColor) &
                    wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@formcontrolname='username']")))).getCssValue("border-color").toString().equals(expectedBorderColor) &
                    wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@formcontrolname='password']")))).getCssValue("border-color").toString().equals(expectedBorderColor)
            ) {
                actualBorderColor = expectedBorderColor;
            }
            assertEquals(expectedBorderColor, actualBorderColor, "One of field not highlighted by red");

             */

            actualBorderColor = wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@formcontrolname='name']")))).getCssValue("border-color");
            assertEquals(expectedBorderColor, actualBorderColor, "'Name' field populated, but highlighted by red");

            actualBorderColor = wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@formcontrolname='username']")))).getCssValue("border-color");
            assertEquals(expectedBorderColor, actualBorderColor, "'Email' field not populated, but not highlighted by red");

            actualBorderColor = wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@formcontrolname='password']")))).getCssValue("border-color");
            assertEquals(expectedBorderColor, actualBorderColor, "'Password' field not populated, but not highlighted by red");

        } catch (Exception e) {
            System.err.println("Error open site : " + e.getMessage());
        }
        driver.quit();

    }

    @Test
    public static void negativeRegistrationAttemptTwoFields() {

        try {
            String expectedBorderColor = "rgb(248, 65, 71)";
            String actualBorderColor = "";
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


            //print for debug
            System.out.println(wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@formcontrolname='name']")))).getCssValue("border-color")); //formcontrolname="name"
            System.out.println(wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@formcontrolname='username']")))).getCssValue("border-color")); //formcontrolname="username"
            //comment out the line below to fail the test
            System.out.println(wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@formcontrolname='password']")))).getCssValue("border-color")); //formcontrolname="name"

            // get CssValue for fields
            actualBorderColor = wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@formcontrolname='name']")))).getCssValue("border-color");
            assertNotEquals(expectedBorderColor, actualBorderColor, "'Name' field populated, but highlighted by red");

            actualBorderColor = wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@formcontrolname='username']")))).getCssValue("border-color");
            assertEquals(expectedBorderColor, actualBorderColor, "'Email' field not populated, but not highlighted by red");

            actualBorderColor = wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@formcontrolname='password']")))).getCssValue("border-color");
            assertEquals(expectedBorderColor, actualBorderColor, "'Password' field not populated, but not highlighted by red");


        } catch (Exception e) {
            System.err.println("Error open site : " + e.getMessage());
        }
        driver.quit();

    }


    @AfterTest
    public void after_test() {
        driver.quit();
        System.out.println("After test execution..");
    }


}




