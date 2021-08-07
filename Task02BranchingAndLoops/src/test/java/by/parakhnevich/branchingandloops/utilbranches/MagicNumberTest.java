package by.parakhnevich.branchingandloops.utilbranches;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MagicNumberTest {

    MagicNumber magicNumber;

    @DataProvider(name = "positiveDataForFindingMagicNumber")
    public Object[][] createPositiveDataForFindingMagicNumber() {
        return new Object[][]{
                {123 , 12 * 7},
                {233, 2 * 7},
                {113 , 11 * 7},
                {-123, -12 * 7}
        };
    }

    @Test(description = "Test with positive scenario for find",
    dataProvider = "positiveDataForFindingMagicNumber")
    public void testFind1(int number , int expectedNumber) {
        magicNumber = new MagicNumber(number);
        Assert.assertEquals(magicNumber.find() , expectedNumber);
    }

    @Test(description = "Test with exception . 333 - all biggest numbers is void",
            expectedExceptions = NumberFormatException.class)
    public void testFind2() {
        magicNumber = new MagicNumber(333);
        magicNumber.find();
    }
}