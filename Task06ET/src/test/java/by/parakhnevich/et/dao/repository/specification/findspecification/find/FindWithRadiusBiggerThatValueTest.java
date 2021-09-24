package by.parakhnevich.et.dao.repository.specification.findspecification.find;

import by.parakhnevich.et.bean.Sphere;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FindWithRadiusBiggerThatValueTest {
    Sphere sphere = new Sphere(1,1,1,1);
    FindWithRadiusBiggerThatValue find1 = new FindWithRadiusBiggerThatValue(0);
    FindWithRadiusBiggerThatValue find2 = new FindWithRadiusBiggerThatValue(2);

    @Test
    public void testIsSatisfiedBy() {
        Assert.assertTrue(find1.isSatisfiedBy(sphere));
        Assert.assertFalse(find2.isSatisfiedBy(sphere));
    }
}