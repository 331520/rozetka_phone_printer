package utils;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformer  implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
        System.out.println("method : " + method.getName());
        if (method.getName().equals("notebookFiltersCheckA")) {
            System.out.println("set data provider for " + method.getName());
            iTestAnnotation.setDataProviderClass(DataProviderFactory.class);
            iTestAnnotation.setDataProvider("getProducerNameA");
        } else if (method.getName().equals("notebookFiltersCheckM")) {
            System.out.println("set data provider for " + method.getName());
            iTestAnnotation.setDataProviderClass(DataProviderFactory.class);
            iTestAnnotation.setDataProvider("getProducerNameM");
        }
        iTestAnnotation.setRetryAnalyzer(RetAnalyzer.class);
    }
}
