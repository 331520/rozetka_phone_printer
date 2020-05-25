package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Screenshot {
    private final WebDriver driver;
    public Screenshot(WebDriver driver) {
        this.driver = driver;
    }

    public void getScreenshot(ITestResult testResult) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        String screenshotName = "screenshots/" + testResult.getTestClass().getName().replace(".", "/")
                + "/" + testResult.getMethod().getConstructorOrMethod().getName()+".png";
        try {
            FileUtils.copyFile(src, new File(screenshotName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
