package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetAnalyzer implements IRetryAnalyzer {
    private int curCount = 1;
    final int MAX_ATTEMPT = 2;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (curCount < MAX_ATTEMPT){
            System.out.println("curCount : " + curCount);
            curCount++;
            return true;
        } else {
            return false;
        }


    }
}
