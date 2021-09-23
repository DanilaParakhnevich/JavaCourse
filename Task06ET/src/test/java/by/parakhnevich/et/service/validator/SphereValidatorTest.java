package by.parakhnevich.et.service.validator;

import by.parakhnevich.et.bean.Sphere;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SphereValidatorTest {
    SphereValidator validator = new SphereValidator();

    @Test
    public void testValidate() {
        assertTrue(validator.
                validate(new Sphere(1, 1, 1, 1)));
        assertFalse(validator.
                validate(new Sphere(0,1,1,1)));
    }
}