package by.parakhnevich.et.dao.repository.specification.findspecification.find;

import by.parakhnevich.et.bean.Sphere;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FindByPointTest {
    Sphere sphere = new Sphere(1,1,1,1);
    FindByPoint find1 = new FindByPoint(1,1,1);
    FindByPoint find2 = new FindByPoint(21,22,1);


    @Test
    public void testIsSatisfiedBy() {
        Assert.assertTrue(find1.isSatisfiedBy(sphere));
        Assert.assertFalse(find2.isSatisfiedBy(sphere));
    }
}