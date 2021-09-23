package by.parakhnevich.multithreadingmatrix.service.threadformatrix.dequethread;

import by.parakhnevich.multithreadingmatrix.bean.Matrix;
import by.parakhnevich.multithreadingmatrix.service.PutNumbersInMainDiagonal;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.PutterThread;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Deque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ThreadWithDeque extends PutterThread {
    Deque<Integer> indexes;
    Semaphore semaphore;
    Logger logger = (Logger) LogManager.getLogger(ThreadWithDeque.class);

    public ThreadWithDeque(Matrix matrix, Deque<Integer> indexes, Semaphore semaphore, int number) {
        super(matrix, number);
        this.indexes = indexes;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            while (!indexes.isEmpty()) {
                semaphore.acquire();
                matrix.put(indexes.getFirst(), indexes.removeFirst(), number);
                PutNumbersInMainDiagonal.inc();//для теста
                String logInfo = getName() + " put " + number;
                logger.log(Level.INFO, logInfo);
                semaphore.release();
                TimeUnit.MILLISECONDS.sleep(50);
            }
        } catch (InterruptedException e) {
            logger.error(e);
        }
    }
}
