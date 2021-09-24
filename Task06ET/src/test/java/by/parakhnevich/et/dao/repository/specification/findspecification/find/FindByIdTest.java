package by.parakhnevich.et.dao.repository.specification.findspecification.find;

import by.parakhnevich.et.bean.Sphere;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FindByIdTest {
    Sphere sphere = new Sphere(1,1,1,1);
    FindById find1 = new FindById(0);
    FindById find2 = new FindById(1);

    @Test
    public void testIsSatisfiedBy() {
        Assert.assertTrue(find1.isSatisfiedBy(sphere));
        Assert.assertFalse(find2.isSatisfiedBy(sphere));
    }
}