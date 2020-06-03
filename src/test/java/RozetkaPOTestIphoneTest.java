import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobject.IndexPage;

import static org.testng.Assert.assertEquals;

public class RozetkaPOTestIphoneTest extends TestBaseSetup {
    IndexPage indexPage;

    @BeforeMethod
    public void init() {
        indexPage = new IndexPage(driver);
    }

    @Test
    public void testMethod1(){
        indexPage.openPage("https://www.google.com/");
    }

    @Test
    public void testMethod2(){
        indexPage.openPage("https://rozetka.com.ua/");

    }



    //mvn clean -Dtest=RozetkaPOTest#iPhoneOnly test
/*    @Test
    public void iPhoneOnly() {
        String expectedState = "iPhone";

        indexPage.openPage("https://rozetka.com.ua/");
        indexPage.setSearch(expectedState);
        indexPage.detectIfIphoneItemsOnPage();

        //check for items card. All items mus me "iPhone"
        String actualState = indexPage.detectAllCardsForIfone(expectedState);
        assertEquals(actualState, expectedState, "Error on page. Some item isn't iPhone: " + actualState);
    }*/

}
