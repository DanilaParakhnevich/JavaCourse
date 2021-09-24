package by.parakhnevich.et.dao.repository.specification.findspecification.find;

import by.parakhnevich.et.bean.Sphere;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FindWithRadiusLessThatValueTest {
    Sphere sphere = new Sphere(1,1,1,1);
    FindWithRadiusLessThatValue find1 = new FindWithRadiusLessThatValue(2);
    FindWithRadiusLessThatValue find2 = new FindWithRadiusLessThatValue(1);

    @Test
    public void testIsSatisfiedBy() {
        Assert.assertTrue(find1.isSatisfiedBy(sphere));
        Assert.assertFalse(find2.isSatisfiedBy(sphere));
    }
}