package utils;

import org.testng.annotations.DataProvider;

public class DataProviderFactory {

    @DataProvider
    public static Object[][] getDp1() {
        return new Object[][]{{"one"}, {"two"}, {"three"}};
    }

    @DataProvider
    public Object[] getProducerNameA() {
        return new Object[][]{
                {"Asus"},
                {"Apple"},
                {"Acer"}
        };
    }

    @DataProvider
    public Object[] getProducerNameM() {
        return new Object[][]{
                {"MSI"},
                {"Microsoft"}
        };
    }

}
