package by.parakhnevich.utillinearalgorithm.finder;

import by.parakhnevich.entity.FunctionWithThreeVariables;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FindVariableOfFuncWithThreeVariablesTest {
    FunctionWithThreeVariables function;
    FindVariableOfFuncWithThreeVariables finder;

    @BeforeMethod
    public void setUp() {
        function = new FunctionWithThreeVariables(3,2,0);
        finder = new FindVariableOfFuncWithThreeVariables(function);
    }

    @Test
    public void countResultPositiveTest() {
        Assert.assertEquals(finder.countResult(),0);
    }
}