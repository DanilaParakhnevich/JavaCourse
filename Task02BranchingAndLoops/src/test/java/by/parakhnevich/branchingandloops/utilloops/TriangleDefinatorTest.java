package by.parakhnevich.branchingandloops.utilloops;

import by.parakhnevich.branchingandloops.bean.Triangle;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

public class TriangleDefinatorTest {

    private TriangleDefinator triangleDefinator;

    @DataProvider(name = "positiveDataForTestingIsTriangle")
    public Object[][] createPositiveDataForTestingIsTriangle(){
        return new Object[][]{
                {90.0 , 90.0 , false},
                {0.0 , 90.0 , false},
                {45.0 , 45.0 , true},
                {-1.0 , 89.0 , false}
        };
    }

    @Test(description = "Test with positive scenario for testing " +
            "is it triangle",
    dataProvider = "positiveDataForTestingIsTriangle")
    public void testIsTriangle(double first, double second, boolean value) {
        triangleDefinator = new TriangleDefinator(
                new Triangle(Arrays.asList(first , second)
                ));
        Assert.assertEquals(triangleDefinator.isTriangle(),value);
    }

    @DataProvider(name = "positiveDataForTestingIsTriangleRectangular")
    public Object[][] createPositiveDataForTestingIsTriangleRectangular(){
        return new Object[][]{
                {90 , 45 , true},
                {45 , 45 , true},
                {0 , 90 , false},
                {-90 , -45 , false}
        };
    }


    @Test(description = "Test with positive scenario for testing " +
            "is it rectangular triangle",
            dataProvider = "positiveDataForTestingIsTriangleRectangular")
    public void testIsRectangular(double first, double second , boolean value) {
        triangleDefinator = new TriangleDefinator(
                new Triangle(Arrays.asList(first,second)
                ));
        Assert.assertEquals(triangleDefinator.isRectangular(),value);
    }
}