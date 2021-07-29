package by.parakhnevich.utillinearalgorithm.calculator;

import by.parakhnevich.entity.RegularTriangle;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RegularTriangleCalcTest {
    RegularTriangle triangleTest;
    RegularTriangleCalc calc;

    @BeforeMethod
    public void setUp() {
        triangleTest = new RegularTriangle(1);
        calc = new RegularTriangleCalc(triangleTest);
    }

    @Test
    public void countInscribedRadiusPositiveTest() {
        Assert.assertEquals(calc.countInscribedRadius(),0.289,0.001);
    }

    @Test
    public void countCircumscribedRadiusPositiveTest() {
        Assert.assertEquals(calc.countCircumscribedRadius(),0.577,0.001);
    }

    @Test
    public void countHeightPositiveTest() {
        Assert.assertEquals(calc.countHeight(),0.866,0.001);
    }

    @Test
    public void countAreaPositiveTest() {
        Assert.assertEquals(calc.countArea(),0.433,0.001);
    }
}