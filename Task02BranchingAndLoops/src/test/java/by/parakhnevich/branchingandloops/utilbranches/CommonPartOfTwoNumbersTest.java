package by.parakhnevich.branchingandloops.utilbranches;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

public class CommonPartOfTwoNumbersTest {

    CommonPartOfTwoNumbers commonPartOfTwoNumbers;
    @DataProvider(name = "positiveDataForCommonPartOfTwoNumbersFinding")
    public Object[][] createPositiveDataForCommonPartFinding() {
        return new Object[][]{
                {new int[]{123456 , 136},
                        "136"},
                {new int[]{11111 , 123},
                       "1"},
                {new int[]{11111,222222},
                        ""}
        };
    }

    @Test(description = "Test with positive scenario for finding common elements",
    dataProvider = "positiveDataForCommonPartOfTwoNumbersFinding")
    public void findTest(int[] ar,String expectedResult){
        commonPartOfTwoNumbers = new CommonPartOfTwoNumbers(ar[0],ar[1]);
        Assert.assertEquals(commonPartOfTwoNumbers.find(),expectedResult);
    }
}