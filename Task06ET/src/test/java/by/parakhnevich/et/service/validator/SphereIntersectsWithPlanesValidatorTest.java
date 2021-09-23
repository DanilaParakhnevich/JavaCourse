package by.parakhnevich.et.service.validator;

import by.parakhnevich.et.bean.Sphere;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SphereIntersectsWithPlanesValidatorTest {
    SphereIntersectsWithPlanesValidator validator =
            new SphereIntersectsWithPlanesValidator();
    @Test
    public void testValidate() {
        assertTrue(validator.
                validate(new Sphere(1, 1, 1, 1)));
        assertFalse(validator.
                validate(new Sphere(1,2,1,1)));
        assertTrue(validator.validate(new Sphere(5,2,-1,0)));
    }
}