package by.parakhnevich.branchingandloops.utilbranches;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SumOfSimpleSeriesTest {

    private static double EPSILON = 0.01;
    SumOfSimpleSeries sumOfSeries;


    @DataProvider(name = "positiveDataForFindingSumOfSimpleSeries")
    public Object[][] createPositiveDataForFindingSumOfSimpleSeries() {
        return new Object[][]{
                {1,1},
                {2,1.5},
                {0,0},
                {-100,0},
                {Integer.MAX_VALUE,22.0648}};
    }

    @Test(description = "Test with positive scenario for calculating sum of simple series",
    dataProvider = "positiveDataForFindingSumOfSimpleSeries")
    public void testCalculate(int number, double expectedNumber) {
        sumOfSeries = new SumOfSimpleSeries(number);
        Assert.assertEquals(sumOfSeries.calculate() , expectedNumber , EPSILON);
    }
}