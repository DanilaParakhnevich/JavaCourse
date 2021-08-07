package by.parakhnevich.branchingandloops.utilloops;

import by.parakhnevich.branchingandloops.utilbranches.SumOfSimpleSeries;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class ArePointsOnLineTest {

    ArePointsOnLine arePointsOnLine;


    @DataProvider(name = "positiveDataForDefinition")
    public Object[][] createPositiveDataForDefinition() {
        return new Object[][]{
                {Arrays.asList(Double.MAX_VALUE, Double.MAX_VALUE, Double.MIN_VALUE, Double.MIN_VALUE,
                        0.0, 0.0), true},
                {Arrays.asList(1.0, 1.0, 0.0, 0.0, -1.0, -1.0), true},
                {Arrays.asList(1.0, 2.0, 3.0, 6.0, 7.0, 8.0), false}};
    }

    @Test(description = "Test with positive scenario for defining are 3 points" +
            " on 1 line",
            dataProvider = "positiveDataForDefinition")
    public void testDefine(List<Double> list,boolean value) {
        arePointsOnLine = new ArePointsOnLine(list);
        Assert.assertEquals(arePointsOnLine.define(),value);
    }
}