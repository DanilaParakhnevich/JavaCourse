package by.parakhnevich.multithreadingmatrix.service.threadformatrix.countdownlatchthread;

import by.parakhnevich.multithreadingmatrix.bean.Matrix;
import by.parakhnevich.multithreadingmatrix.service.PutNumbersInMainDiagonal;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.PutterThread;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ThreadWithCountDownLatch extends PutterThread {
    CountDownLatch latch;
    Logger logger = (Logger) LogManager.getLogger(ThreadWithCountDownLatch.class);

    public ThreadWithCountDownLatch(Matrix matrix, CountDownLatch latch, int number) {
        super(matrix, number);
        this.latch = latch;
    }

    @Override
    public void run() {
        while (true) {
            latch.countDown();
            if (PutNumbersInMainDiagonal.index >= matrix.getRows()) {
                break;
            }
            matrix.put(PutNumbersInMainDiagonal.index, PutNumbersInMainDiagonal.index, number);
            String logInfo = getName() + " put " + number;
            logger.log(Level.INFO, logInfo);
            PutNumbersInMainDiagonal.inc();
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
    }
}
