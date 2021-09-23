package by.parakhnevich.et.service.calculator;

import by.parakhnevich.et.bean.Sphere;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SphereCalculatorTest {
    Sphere sphere1 = new Sphere(1,1,1,1);
    Sphere sphere2 = new Sphere(3,1,1,1);
    double delta = 1e-5;

    @Test
    public void testCalculateVolume() {
        assertEquals(new SphereCalculator(sphere1).calculateArea(),4 * Math.PI);
        assertEquals(new SphereCalculator(sphere2).calculateArea(), 36 * Math.PI);
    }

    @Test
    public void testCalculateArea() {
        assertEquals(new SphereCalculator(sphere1).calculateVolume(),4.0  / 3 * Math.PI, delta);
        assertEquals(new SphereCalculator(sphere2).calculateVolume(), 36 * Math.PI, delta);
    }
}