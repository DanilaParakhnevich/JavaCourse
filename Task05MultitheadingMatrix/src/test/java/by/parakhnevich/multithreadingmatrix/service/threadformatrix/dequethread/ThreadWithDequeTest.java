package by.parakhnevich.multithreadingmatrix.service.threadformatrix.dequethread;

import by.parakhnevich.multithreadingmatrix.bean.Matrix;
import by.parakhnevich.multithreadingmatrix.dao.MatrixMultithreadingDAO;
import by.parakhnevich.multithreadingmatrix.dao.exception.DAOException;
import by.parakhnevich.multithreadingmatrix.service.PutNumbersInMainDiagonal;
import by.parakhnevich.multithreadingmatrix.service.creators.ThreadsWithDequeCreator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ThreadWithDequeTest {
    List<Integer> firstList = List.of(2);
    List<Integer> secondList = List.of(1);
    List<Integer> thirdList = Arrays.asList(0, 0, 0);
    List<Integer> fourthList = Arrays.asList(2, 3, 1, 5, 4, 2, 1, 5);

    @DataProvider(name = "positiveDataForDequeThread1")
    public Object[][] createPositiveDataForDequeThread1() throws DAOException, IOException {
        return new Object[][]{
                {new MatrixMultithreadingDAO().getMatrix(),
                        firstList,
                        copyMatrix(new Integer[][]{
                                {2, 2, 3, 3, 2},
                                {1, 2, 5, 2, 1},
                                {6, 8, 2, 1, 4},
                                {1, 2, 3, 2, 5},
                                {2, 5, 1, 3, 2}})
                }
        };
    }

    @DataProvider(name = "positiveDataForDequeThread2")
    public Object[][] createPositiveDataForDequeThread2() throws DAOException, IOException {
        return new Object[][]{
                {new MatrixMultithreadingDAO().getMatrix(),
                        secondList,
                        copyMatrix(new Integer[][]{
                                {1, 2, 3, 3, 2},
                                {1, 1, 5, 2, 1},
                                {6, 8, 1, 1, 4},
                                {1, 2, 3, 1, 5},
                                {2, 5, 1, 3, 1}}),
                }
        };
    }

    @DataProvider(name = "positiveDataForDequeThread3")
    public Object[][] createPositiveDataForDequeThread3() throws DAOException, IOException {
        return new Object[][]{
                {new MatrixMultithreadingDAO().getMatrix(),
                        thirdList,
                        copyMatrix(new Integer[][]{
                                {0, 2, 3, 3, 2},
                                {1, 0, 5, 2, 1},
                                {6, 8, 0, 1, 4},
                                {1, 2, 3, 0, 5},
                                {2, 5, 1, 3, 0}}),
                }
        };
    }

    @DataProvider(name = "positiveDataForDequeThread4")
    public Object[][] createPositiveDataForDequeThread4() throws DAOException, IOException {
        return new Object[][]{
                {new MatrixMultithreadingDAO().getMatrix(),
                        fourthList,
                        copyMatrix(new Integer[][]{
                                {2, 2, 3, 3, 2},
                                {1, 3, 5, 2, 1},
                                {6, 8, 1, 1, 4},
                                {1, 2, 3, 5, 5},
                                {2, 5, 1, 3, 4}})
                }
        };
    }








    @Test(description = "Test1 with positive scenario for usability" +
            " of thread with Deque",
            dataProvider = "positiveDataForDequeThread1")
    public void testRun1(Matrix matrix1, List<Integer> list, Matrix matrix) throws InterruptedException {
        new PutNumbersInMainDiagonal().execute(new ThreadsWithDequeCreator().create(list, matrix1));
        TimeUnit.MILLISECONDS.sleep(100L * matrix.getRows());
        Assert.assertEquals(matrix1, matrix);
    }

    @Test(description = "Test2 with positive scenario for usability" +
            " of thread with Deque",
            dataProvider = "positiveDataForDequeThread2")
    public void testRun2(Matrix matrix1, List<Integer> list, Matrix matrix) throws InterruptedException {
        new PutNumbersInMainDiagonal().execute(new ThreadsWithDequeCreator().create(list, matrix1));
        TimeUnit.MILLISECONDS.sleep(100L * matrix.getRows());
        Assert.assertEquals(matrix1, matrix);
    }

    @Test(description = "Test3 with positive scenario for usability" +
            " of thread with Deque",
            dataProvider = "positiveDataForDequeThread3")
    public void testRun3(Matrix matrix1, List<Integer> list, Matrix matrix) throws InterruptedException {
        new PutNumbersInMainDiagonal().execute(new ThreadsWithDequeCreator().create(list, matrix1));
        TimeUnit.MILLISECONDS.sleep(100L * matrix.getRows());
        Assert.assertEquals(matrix1, matrix);
    }

    @Test(description = "Test4 with positive scenario for usability" +
            " of thread with Deque",
            dataProvider = "positiveDataForDequeThread4")
    public void testRun4(Matrix matrix1, List<Integer> list, Matrix matrix) throws InterruptedException {
        new PutNumbersInMainDiagonal().execute(new ThreadsWithDequeCreator().create(list, matrix1));
        TimeUnit.MILLISECONDS.sleep(100L * matrix.getRows());
        Assert.assertEquals(matrix1, matrix);
    }

    private Matrix copyMatrix(Integer[][] array){
        Matrix matrix = new Matrix(array.length, array.length);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                matrix.put(i, j, array[i][j]);
            }
        }
        return matrix;
    }
}