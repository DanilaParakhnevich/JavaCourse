package by.parakhnevich.arraysanddecomposition.utilarrays;

import by.parakhnevich.arraysanddecomposition.bean.Array;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CocktailSortingTest {
    @DataProvider(name = "positiveDataForSorting")
    public Object[][] createPositiveDataForSorting() {
        return new Object[][]{
                {new Array<Number>(new Integer[]{
                        2, 1, 3, -2, -3, 1 ,6
                }),
                new Array<Number>(new Integer[]{
                        -3, -2, 1, 1, 2, 3, 6
                })},
                {new Array<Number>(new Integer[]{
                        Integer.MIN_VALUE, Integer.MAX_VALUE, 0
                }),
                new Array<Number>(new Integer[]{
                        Integer.MIN_VALUE, 0, Integer.MAX_VALUE
                })},
                {new Array<Number>(new Integer[]{
                        1, 1, 1, 1, 1, 1, 1, 1
                }),
                new Array<Number>(new Integer[]{
                        1, 1, 1, 1, 1, 1, 1, 1
                })},
                {new Array<Number>(new Integer[]{
                        1, 3, 4, 0
                }),
                new Array<Number>(new Integer[]{
                        0, 1, 3, 4
                })},
        };
    }

    @Test(description = "Test with positive scenario for sorting",
            dataProvider = "positiveDataForSorting")
    public void testSort(Array<Number> array, Array<Number> result) {
        new CocktailSorting().sort(array);
        Assert.assertEquals(array, result);
    }
}