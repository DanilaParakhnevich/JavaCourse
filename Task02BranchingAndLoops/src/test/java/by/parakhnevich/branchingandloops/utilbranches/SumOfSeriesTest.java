package by.parakhnevich.branchingandloops.utilbranches;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeoutException;

import static org.testng.Assert.*;

public class SumOfSeriesTest {

    double EPSILON = 0.01;
    SumOfSeries sumOfSeries;

    @DataProvider(name = "positiveDataForFindingSumOfSeries")
    public Object[][] createPositiveDataForFindingSumOfSimpleSeries() {
        return new Object[][]{
                {0.01,1.4837},
                {0.5,0.8333},
                {1,0},
                {Integer.MAX_VALUE,0}};
    }

    @Test(description = "Test with positive scenario for calculating sum of series",
            dataProvider = "positiveDataForFindingSumOfSeries")
    public void testCalculate(double number, double expectedNumber) {
        sumOfSeries = new SumOfSeries(number);
        Assert.assertEquals(sumOfSeries.calculate() , expectedNumber , EPSILON);
    }
}