import io.qameta.allure.Feature;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobject.IndexPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class RozetkaPOTest extends TestBaseSetup {
    IndexPage indexPage;


    @BeforeMethod
    public void init() {
        indexPage = new IndexPage(driver);
    }

    @Feature("Open Rozetka")
    @Test
    public void testMethod1(){
        indexPage.openPage("https://rozetka.com.ua/");
    }

    @Feature("Open Google")
    @Test
    public void testMethod2(){
        indexPage.openPage("https://www.google.com/");
        fail();
    }



    //mvn clean -Dtest=RozetkaPOTest#samsungOnly test
   /* @Test
    public void samsungOnly() {
        String expectedState = "samsung";
        Integer expectedCategoriesAmount  = 9;
        indexPage.openPage("https://rozetka.com.ua/");
        indexPage.setSearch(expectedState);
        indexPage.detectIfSamsungItemsOnPage();

        //Check that all categories displayed
        Integer actualCategoriesAmount = indexPage.countAllSamsungCategories(expectedState);
        assertEquals(actualCategoriesAmount, expectedCategoriesAmount, "Error on page. Expected categories amount : "
                + expectedCategoriesAmount + " biu found " + actualCategoriesAmount + " categories");

        String actualState = indexPage.detectAllProducer(expectedState);
        assertEquals(actualState, expectedState, "Error on page. non-Samsung category : " + actualState);
    }

    //mvn clean -Dtest=RozetkaPOTest#notebookFiltersCheck test
    @Test(dataProvider = "getProducerName")
      public void notebookFiltersCheck(String producer) {
          String expectedState = producer;
        indexPage.openPage("https://rozetka.com.ua/notebooks/c80004/preset=workteaching/");
        //indexPage.setSearch(expectedState);
        indexPage.detectIfIphoneItemsOnPage();
        //indexPage.setFilter(producer);
        indexPage.setFilter(expectedState);
        indexPage.detectIfIphoneItemsOnPage();
        //check for items card. All items mus me "iPhone"
        String actualState = indexPage.detectAllCardsForIfone(expectedState);
        assertEquals(actualState, expectedState, "Error on page. Some item not from '"+producer+"' producer : " + actualState);
    }


    @DataProvider
    public Object[] getProducerName() {
        return new Object[][]{
                {"Asus"},
                {"Apple"},
                {"Acer"}
        };
    }*/


}
