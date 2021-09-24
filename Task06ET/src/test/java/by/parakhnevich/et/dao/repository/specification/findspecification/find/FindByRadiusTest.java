package by.parakhnevich.et.dao.repository.specification.findspecification.find;

import by.parakhnevich.et.bean.Sphere;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FindByRadiusTest {
    Sphere sphere = new Sphere(1,1,1,1);
    FindByRadius find1 = new FindByRadius(1);
    FindByRadius find2 = new FindByRadius(0);

    @Test
    public void testIsSatisfiedBy() {
        Assert.assertTrue(find1.isSatisfiedBy(sphere));
        Assert.assertFalse(find2.isSatisfiedBy(sphere));
    }
}