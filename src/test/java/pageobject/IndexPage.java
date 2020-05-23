package pageobject;

import com.sun.org.apache.xpath.internal.objects.XString;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


public class IndexPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    public String ctl;
    private String searchString;
    private String eState;

    By search = By.name("search"); //search field
    By catalogIphone =  By.xpath("//*[@class='catalog-grid__cell catalog-grid__cell_type_slim']"); //catalog of iphones items
    By leftPanelItems =  By.xpath("//*[@name='menu_categories_left']"); // left panel categories
    By links = By.tagName("a"); // search all links


    public IndexPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 20);
    }

    public IndexPage openPage(){
        driver.get("https://rozetka.com.ua/");
        return this;
    }

    public IndexPage setSearch(String searchString){
        this.searchString = searchString;
        WebElement searchEl = driver.findElement(search);
        wait.until(ExpectedConditions.elementToBeClickable(searchEl));
        searchEl.sendKeys(this.searchString);
        searchEl.sendKeys(Keys.ENTER);
        return this;
    }

    public IndexPage detectIfIphoneItemsOnPage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(catalogIphone));
        System.out.println("Goods at the page");
        return this;
    }

    public IndexPage detectIfSamsungItemsOnPage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(leftPanelItems));
        System.out.println("Goods at the page");
        return this;
    }

    public String detectAllCardsForIfone(String eState){
        this.eState=eState;
        List<WebElement> listOfElementsIphone = driver.findElements(catalogIphone);
        System.out.println("items was found : " + listOfElementsIphone.size());
        for (WebElement element : listOfElementsIphone) {
            if (!element.getText().contains(eState)) {
            //if (!element.getText().contains("Meizu")) {
                //System.out.println(element.getText());
                this.eState = element.getText();
                break;
            }
        }
        return this.eState;
    }

    public String detectAllProducer(String eState){
        this.eState=eState;
        String hrf; //category url
        String txt; //category url's text

        //list of categories
        List<WebElement> listOfleftPanelItems = driver.findElements(leftPanelItems);

        //check each of category
        for (WebElement element : listOfleftPanelItems) {
            hrf = element.findElement(links).getAttribute("href");
            txt = element.findElement(links).getText();
            if (hrf.contains("producer") & !hrf.contains(eState)) {
            //if (hrf.contains("producer") & !hrf.contains("Meizu")) { //uncomment for getting the failed test
                this.eState = txt;
                break;
            }
        }
        return this.eState;
    }
    public Integer countAllSamsungCategories(String eState){
        //list of categories
        List<WebElement> listOfleftPanelItems = driver.findElements(leftPanelItems);
        System.out.println("items was found : " + listOfleftPanelItems.size());
        return listOfleftPanelItems.size();
    }
}
