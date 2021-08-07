package by.parakhnevich.branchingandloops.utilloops;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class CurrentOfPositiveNumbersTest {
    CurrentOfPositiveNumbers currentOfPositiveNumbers;

    @DataProvider(name = "positiveDataForCountingCurrentOfPositive")
    public Object[][] createPositiveDataForCounting() {
        return new Object[][]{
                {Arrays.asList(-1.0,0.0,0.0),0},
                {Arrays.asList(1.0, 1.0, 0.0), 2},
                {Arrays.asList(1.0, 2.0, 3.0), 3}};
    }

    @Test(description = "Test with positive scenario for counting" +
            "current of positive numbers",
            dataProvider = "positiveDataForCountingCurrentOfPositive")
    public void testDefine(List<Double> list, int value) {
        currentOfPositiveNumbers = new CurrentOfPositiveNumbers(list);
        Assert.assertEquals(currentOfPositiveNumbers.count(),value);
    }

}