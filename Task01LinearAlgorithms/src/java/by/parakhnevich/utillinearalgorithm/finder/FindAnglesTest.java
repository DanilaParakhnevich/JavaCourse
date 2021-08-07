package by.parakhnevich.utillinearalgorithm.finder;

import by.parakhnevich.entity.Triangle;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Math.toDegrees;
import static org.testng.Assert.*;

public class FindAnglesTest {
    Triangle triangle;
    FindAngles angles;

    @BeforeMethod
    public void setUp() {
        triangle = new Triangle(2,2,2);
        angles = new FindAngles(triangle);
    }

    @Test
    public void countAngleAPositiveTest() {
        Assert.assertEquals(angles.countAngleA(),toDegrees(angles.countAngleA()),60);
    }

    @Test
    public void countAngleBPositiveTest() {
        Assert.assertEquals(angles.countAngleA(),toDegrees(angles.countAngleB()),60);
    }

    @Test
    public void countAngleCPositiveTest() {
        Assert.assertEquals(angles.countAngleA(),toDegrees(angles.countAngleC()),60);
    }
}