package by.parakhnevich.branchingandloops.utilloops.comparator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

public class TwoNumbersComparatorTest {

    TwoNumbersComparator twoNumbersComparator;

    @DataProvider(name = "positiveDataForCompareNumbers")
    public Object[][] createPositiveDataForCompareNumbers(){
        return new Object[][]{
                {1,1,true},
                {Double.MAX_VALUE , Double.MIN_VALUE , false},
                {-1,1,false}
        };
    }

    @Test(description = "Positive test for comparing two double values",
    dataProvider = "positiveDataForCompareNumbers")
    public void testCompare(double first,double second, boolean value) {
        twoNumbersComparator = new TwoNumbersComparator(first,second);
        Assert.assertEquals(twoNumbersComparator.compare(),value);
    }
}