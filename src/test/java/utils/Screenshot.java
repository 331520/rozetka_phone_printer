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
        String methodName = testResult.getMethod().getConstructorOrMethod().getName();
        //System.out.println(methodName + " param 0 : " + testResult.getParameters()[0]);

        String screenshotName;
        if (methodName.equals("notebookFiltersCheck")) { //notebookFiltersCheck method repeates 3 times with different parameters
            screenshotName = "screenshots/" + testResult.getTestClass().getName().replace(".", "/")
                    + "/" + methodName + "_" + testResult.getParameters()[0] +".png";
        } else {
            screenshotName = "screenshots/" + testResult.getTestClass().getName().replace(".", "/")
                    + "/" + methodName+".png";
        }



        try {
            FileUtils.copyFile(src, new File(screenshotName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
