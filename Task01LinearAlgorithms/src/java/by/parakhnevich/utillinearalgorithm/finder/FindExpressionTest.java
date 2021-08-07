package by.parakhnevich.utillinearalgorithm.finder;

import by.parakhnevich.entity.Expression;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FindExpressionTest {
    Expression expressionTest;
    FindExpression findExpressionTest;

    @BeforeMethod
    public void setUp() {
        expressionTest = new Expression(0);
        findExpressionTest = new FindExpression(expressionTest);
    }

    @Test
    public void countResultPositiveTest() {
        Assert.assertEquals(findExpressionTest.countResult(),6);
    }
}