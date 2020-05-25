import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.IndexPage;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class RozetkaPOTest extends TestBaseSetup {
    IndexPage indexPage;


    @BeforeMethod
    public void init() {
        indexPage = new IndexPage(driver);
    }

    //mvn clean -Dtest=RozetkaPOTest#iPhoneOnly test
    @Test
    public void iPhoneOnly() {

        String expectedState = "iPhone";

        indexPage.openPage();
        indexPage.setSearch(expectedState);
        indexPage.detectIfIphoneItemsOnPage();

        //check for items card. All items mus me "iPhone"
        String actualState = indexPage.detectAllCardsForIfone(expectedState);
        assertEquals(actualState, expectedState, "Error on page. Some item isn't iPhone: " + actualState);
    }

    /*
    //mvn clean -Dtest=RozetkaPOTest#samsungOnly test
    @Test
    public void samsungOnly() {
        String expectedState = "samsung";
        Integer expectedCategoriesAmount  = 9;
        indexPage.openPage();
        indexPage.setSearch(expectedState);
        indexPage.detectIfSamsungItemsOnPage();

        //Check that all categories displayed
        Integer actualCategoriesAmount = indexPage.countAllSamsungCategories(expectedState);
        assertEquals(actualCategoriesAmount, expectedCategoriesAmount, "Error on page. Expected categories amount : "
                + expectedCategoriesAmount + " biu found " + actualCategoriesAmount + " categories");
        System.out.println("countAllSamsungCategories passed");

        String actualState = indexPage.detectAllProducer(expectedState);
        assertEquals(actualState, expectedState, "Error on page. non-Samsung category : " + actualState);
        System.out.println("detectAllProducer passed");
    }

     */
}
