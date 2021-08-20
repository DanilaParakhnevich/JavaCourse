package by.parakhnevich.arraysanddecomposition.utilmatrix;

import by.parakhnevich.arraysanddecomposition.bean.Matrix;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MatrixCalculatorTest {

    @DataProvider(name = "positiveDataForSum")
    public Object[][] createPositiveDataForSum() {
        return new Object[][]{
                {new Matrix<Number>(new Integer[][]{
                        {1, 1, 1, 1},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {1, 1, 1, 1}}),
                new Matrix<Number>(new Integer[][]{
                        {-1, -1, -1, -1},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {-1, -1, -1, -1}}),
                new Matrix<Number>(new Integer[][]{
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}}),
                },
                {
                new Matrix<Number>(new Integer[][]{
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}}),
                new Matrix<Number>(new Integer[][]{
                        {23, 21, 12, 23},
                        {0, 2454, 236, 652},
                        {765, 241, 321, 123},
                        {876, 642, 22, 1111}}),
                new Matrix<Number>(new Integer[][]{
                        {23, 21, 12, 23},
                        {0, 2454, 236, 652},
                        {765, 241, 321, 123},
                        {876, 642, 22, 1111}}),
                },
                {new Matrix<Number>(new Integer[][]{
                        {Integer.MAX_VALUE, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}}),
                new Matrix<Number>(new Integer[][]{
                        {1, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}}),
                new Matrix<Number>(new Integer[][]{
                        {Integer.MIN_VALUE, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}})
                }
        };
    }


    @Test(description = "Test with positive scenario for sum" +
            " of matrices",
            dataProvider = "positiveDataForSum")
    public void testSum(Matrix<Number> matrix1, Matrix<Number> matrix2, Matrix<Number> result) {
        Assert.assertEquals(new MatrixCalculator<>().sum(matrix1, matrix2),
                result);
    }

    @DataProvider(name = "positiveDataForMinus")
    public Object[][] createPositiveDataForMinus() {
        return new Object[][]{
                {new Matrix<Number>(new Integer[][]{
                        {1, 1, 1, 1},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {1, 1, 1, 1}}),
                new Matrix<Number>(new Integer[][]{
                        {1, 1, 1, 1},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {1, 1, 1, 1}}),
                new Matrix<Number>(new Integer[][]{
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}}),
                },
                {
                new Matrix<Number>(new Integer[][]{
                        {23, 21, 12, 23},
                        {0, 2454, 236, 652},
                        {765, 241, 321, 123},
                        {876, 642, 22, 1111}}),
                new Matrix<Number>(new Integer[][]{
                        {23, 21, 12, 23},
                        {0, 2454, 236, 652},
                        {765, 241, 321, 123},
                        {876, 642, 22, 1111}}),
                new Matrix<Number>(new Integer[][]{
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}}),
                },
                {new Matrix<Number>(new Integer[][]{
                        {Integer.MAX_VALUE, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}}),
                new Matrix<Number>(new Integer[][]{
                        {-1, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}}),
                new Matrix<Number>(new Integer[][]{
                        {Integer.MIN_VALUE, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}})
                }
        };
    }


    @Test(description = "Test with positive scenario for difference" +
            " of matrices",
            dataProvider = "positiveDataForMinus")
    public void testMinus(Matrix<Number> matrix1, Matrix<Number> matrix2, Matrix<Number> result) {
        Assert.assertEquals(new MatrixCalculator<>().difference(matrix1, matrix2),
                result);
    }

    @DataProvider(name = "positiveDataForMultiMatrices")
    public Object[][] createPositiveDataForMultiMatrices() {
        return new Object[][]{
                {new Matrix<Number>(new Integer[][]{
                        {1, 1, 1, 1},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {1, 1, 1, 1}}),
                new Matrix<Number>(new Integer[][]{
                        {3, 3, 3, 3},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {3, 3, 3, 3}}),
                new Matrix<Number>(new Integer[][]{
                        {6, 6, 6, 6},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {6, 6, 6, 6}})
                },
                {
                new Matrix<Number>(new Integer[][]{
                        {1, 2, 3},
                        {3, 2, 1},
                        {2, -2, -2}}),
                new Matrix<Number>(new Integer[][]{
                        {3, 6, 9},
                        {9, 6, 3},
                        {6, -6, -6}}),
                new Matrix<Number>(new Integer[][]{
                        {39, 0, -3},
                        {33, 24, 27},
                        {-24, 12, 24}})
                }
        };
    }

    @Test(description = "Test with positive scenario for multi" +
            " matrices",
            dataProvider = "positiveDataForMultiMatrices")
    public void testMultiplyMatrices(Matrix<Number> matrix1, Matrix<Number> matrix2, Matrix<Number> result) {
        Assert.assertEquals(new MatrixCalculator<>().multiply(matrix1, matrix2),
                result);
    }

    @DataProvider(name = "positiveDataForMultiNumber")
    public Object[][] createPositiveDataForMultiNumber() {
        return new Object[][]{
                {new Matrix<Number>(new Integer[][]{
                        {1, 1, 1, 1},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {1, 1, 1, 1}}),
                        3,
                new Matrix<Number>(new Integer[][]{
                        {3, 3, 3, 3},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {3, 3, 3, 3}}),
                },
                {
                new Matrix<Number>(new Integer[][]{
                        {1, 2, 3},
                        {3, 2, 1},
                        {2, -2, -2},
                        {0, 1, 0}}),
                3,
                new Matrix<Number>(new Integer[][]{
                        {3, 6, 9},
                        {9, 6, 3},
                        {6, -6, -6},
                        {0, 3, 0}}),
                }
        };
    }


    @Test(description = "Test with positive scenario for multi" +
            " number and matrix",
            dataProvider = "positiveDataForMultiNumber")
    public void testMultiplyNumberWithMatrix(Matrix<Number> matrix, Integer number, Matrix<Number> result) {
        Assert.assertEquals(new MatrixCalculator<>().multiply(matrix, number),
                result);
    }


    @DataProvider(name = "positiveDataForTransposition")
    public Object[][] createPositiveDataForTransposition() {
        return new Object[][]{
                {new Matrix<Number>(new Integer[][]{
                        {1, 1, 1, 1},
                        {-1, -1, -1, -1},
                        {0, 0, 0, 0},
                        {3, 2, 1, 0}}),
                new Matrix<Number>(new Integer[][]{
                        {1, -1, 0, 3},
                        {1, -1, 0, 2},
                        {1, -1, 0, 1},
                        {1, -1, 0, 0}})
                },
                {new Matrix<Number>(new Integer[][]{
                        {25, 36, 49, 64},
                        {5, 6, 7, 8},
                        {5, 6, 7, 8},
                        {1, 1, 1, 1}}),
                new Matrix<Number>(new Integer[][]{
                        {25, 5, 5, 1},
                        {36, 6, 6, 1},
                        {49, 7, 7, 1},
                        {64, 8, 8, 1}
                })},
                {new Matrix<Number>(new Integer[][]{
                        {Integer.MAX_VALUE, Integer.MIN_VALUE, 0},
                        {0, 0, 0},
                        {0, 0, 0}}),
                new Matrix<Number>(new Integer[][]{
                        {Integer.MAX_VALUE, 0, 0},
                        {Integer.MIN_VALUE, 0, 0},
                        {0, 0, 0}})
                }
        };
    }



    @Test(description = "Test with positive scenario for transposition" +
            " of matrix",
    dataProvider = "positiveDataForTransposition")
    public void testTransposition(Matrix<Number> matrix, Matrix<Number> result) {
        Assert.assertEquals(new MatrixCalculator<>().transposition(matrix),
                result);
    }
}