package by.parakhnevich.branchingandloops.utilloops;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;

import static java.lang.Double.NaN;
import static jdk.nashorn.internal.objects.Global.Infinity;
import static org.testng.Assert.*;

public class FunctionCalculatorTest {

    private final double EPSILON = 0.1;
    private FunctionCalculator functionCalculator;

    @DataProvider(name = "positiveDataForCalculatingFunction")
    public Object[][] createPositiveDataForCalculatingFunction(){
        return new Object[][]{
                {Infinity , NaN},
                {Double.MAX_VALUE , -Infinity},
                {0 , - 0.16}
        };
    }

    @Test(description = "Test with positive scenario for calculating" +
            " function",
    dataProvider = "positiveDataForCalculatingFunction")
    public void testCalculate(double x , double expectedResult) {
        functionCalculator = new FunctionCalculator(Collections.singletonList(x));
        Assert.assertEquals(functionCalculator.calculate(),expectedResult,EPSILON);
    }
}