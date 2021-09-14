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

    @DataProvider(name = "positiveDataForDequeThread")
    public Object[][] createPositiveDataForDequeThread() throws DAOException, IOException {
        return new Object[][]{
                {new MatrixMultithreadingDAO().getMatrix(),
                        firstList
                },
                {new MatrixMultithreadingDAO().getMatrix(),
                        secondList
                },
                {new MatrixMultithreadingDAO().getMatrix(),
                        thirdList
                },
                {new MatrixMultithreadingDAO().getMatrix(),
                        fourthList
                }
        };
    }

    @Test(description = "Test with positive scenario for usability" +
            " of thread with Deque",
            dataProvider = "positiveDataForDequeThread")
    public void testRun(Matrix matrix1, List<Integer> list) throws InterruptedException {
        new PutNumbersInMainDiagonal().execute(new ThreadsWithDequeCreator().
                create(list, matrix1));
        TimeUnit.MILLISECONDS.sleep(100L * matrix1.getRows());
        Assert.assertEquals(PutNumbersInMainDiagonal.index, matrix1.getRows());
        PutNumbersInMainDiagonal.reset();
    }
}