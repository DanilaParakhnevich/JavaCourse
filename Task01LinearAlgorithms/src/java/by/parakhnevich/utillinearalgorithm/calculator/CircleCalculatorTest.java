package by.parakhnevich.utillinearalgorithm.calculator;

import by.parakhnevich.entity.Circle;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CircleCalculatorTest {
    CircleCalculator circleCalculatorTest1;
    final static double EPSILUN = 0.000001;

    @BeforeMethod
    public void setUp() {
        circleCalculatorTest1 = new CircleCalculator(new Circle(1));
    }

    @Test
    public void countCircuitPositiveTest() {
        Assert.assertEquals(circleCalculatorTest1.countCircuit(),Math.PI * 2,EPSILUN);
    }

    // TODO: 03.08.2021 description
    @Test
    public void countCircuitNegativeTest() {
        Assert.assertNotEquals(circleCalculatorTest1.countCircuit(),Math.PI,EPSILUN);
    }

    @Test
    public void countAreaPositiveTest() {
        Assert.assertEquals(circleCalculatorTest1.countArea(), Math.PI,EPSILUN);
    }

    @Test
    public void countAreaNegativeTest() {
        Assert.assertNotEquals(circleCalculatorTest1.countArea(),2 * Math.PI,EPSILUN);
    }
}