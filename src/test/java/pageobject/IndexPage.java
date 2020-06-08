package pageobject;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Random;


public class IndexPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    public String ctl;
    private String searchString;
    private String eState;
    private String address;
    private String producer;
    public Logger logger = LogManager.getLogger(IndexPage.class);

    By search = By.name("search"); //search field
    By catalogIphone =  By.xpath("//*[@class='catalog-grid__cell catalog-grid__cell_type_slim']"); //catalog of iphones items
    By leftPanelItems =  By.xpath("//*[@name='menu_categories_left']"); // left panel categories
    By links = By.tagName("a"); // search all links
    By data_filter_name  = By.xpath("//*[@data-filter-name='producer']"); //
    By checkboxFilterLink  = By.className("checkbox-filter__link"); //

    //By producerCheck = By.id(producer);




    public IndexPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 20);
        logger.info("Webdriver created ");
    }

    @Step("Open page")
    public IndexPage openPage(String address){
        this.address = address;
        driver.get(this.address);
        return this;
    }

    public IndexPage setSearch(String searchString){
        logger.info("site opened");
        this.searchString = searchString;
        WebElement searchEl = driver.findElement(search);
        wait.until(ExpectedConditions.elementToBeClickable(searchEl));
        searchEl.sendKeys(this.searchString);
        searchEl.sendKeys(Keys.ENTER);
        return this;
    }

    @Step("Open Iphone Index age")
    public IndexPage detectIfIphoneItemsOnPage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(catalogIphone));
        logger.info("Goods at the page");
        return this;
    }

    @Step("Open Index age")
    public IndexPage detectIfSamsungItemsOnPage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(leftPanelItems));
        logger.info("Goods at the page");
        return this;
    }

    @Step("detect All Cards For Ifone")
    public String detectAllCardsForIfone(String eState){
        logger.info("start to detect all products");
        this.eState=eState;
        logger.debug("Start to detect for : " + this.eState);
        List<WebElement> listOfElementsIphone = driver.findElements(catalogIphone);
        logger.debug("items was found : " + listOfElementsIphone.size());
        for (WebElement element : listOfElementsIphone) {
            logger.debug("\r\n================> Check for '"+this.eState+"' : " + element.getText());
            if (!element.getText().toLowerCase().contains(eState.toLowerCase())) {
            //if (!element.getText().contains("Meizu")) {
                logger.warn("Wrong element was found : " + element.getText());
                this.eState = element.getText();
                break;
            }
        }
        return this.eState;
    }

    public String detectAllProducer(String eState){
        logger.info("start to detect all products");
        this.eState=eState;
        String hrf; //category url
        String txt; //category url's text

        //list of categories
        List<WebElement> listOfleftPanelItems = driver.findElements(leftPanelItems);
        logger.debug("items was found : " + listOfleftPanelItems.size());

        //check each of category
        for (WebElement element : listOfleftPanelItems) {
            hrf = element.findElement(links).getAttribute("href");
            txt = element.findElement(links).getText();
            if (hrf.contains("producer") & !hrf.contains(eState)) {
            //if (hrf.contains("producer") & !hrf.contains("Meizu")) { //uncomment for getting the failed test
                logger.error("Wrong producer was found : " + txt + ". URL :"+ hrf);
                this.eState = txt;
                break;
            }
        }
        return this.eState;
    }
    public Integer countAllSamsungCategories(String eState){
        //list of categories
        List<WebElement> listOfleftPanelItems = driver.findElements(leftPanelItems);
        logger.debug("items was found : " + listOfleftPanelItems.size());
        return listOfleftPanelItems.size();
    }

    public IndexPage setFilter(String producer){
        logger.info("set producer: "+producer+" filter");
        this.producer = producer;
        //WebElement producerCheckbox = driver.findElement(By.id(this.producer));
        WebElement producerCheckbox = driver.findElement(By.xpath("//*[@for='"+this.producer+"']"));
        logger.debug(producerCheckbox.getText());
        producerCheckbox.click();
        return this;
    }

    public IndexPage setRandomFilter(){
        //WebElement producerCheckbox = driver.findElement(By.id(this.producer));

        //List<WebElement> producerCheckbox = driver.findElements(By.xpath("//*[@for='"+this.producer+"']"));
        //locate producers
        WebElement dataFilterNameWE = driver.findElement(data_filter_name);
        List<WebElement> producerCheckbox = dataFilterNameWE.findElements(checkboxFilterLink);
        Random random = new Random();
        int randomProducerId =  random.nextInt(producerCheckbox.size());
        WebElement randomProducerWE = producerCheckbox.get(randomProducerId);
        System.out.println("We will click : "+ randomProducerWE.getText());
        System.out.println("We check      : "+ randomProducerWE.getAttribute("href"));
        randomProducerWE.click();

        //delete it before pull to git
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("producerCheckbox.size() : " + producerCheckbox.size());

        return this;
    }


}
