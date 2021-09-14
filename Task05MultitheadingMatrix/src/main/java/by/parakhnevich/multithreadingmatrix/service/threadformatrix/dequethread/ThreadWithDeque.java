package by.parakhnevich.multithreadingmatrix.service.threadformatrix.dequethread;

import by.parakhnevich.multithreadingmatrix.bean.Matrix;
import by.parakhnevich.multithreadingmatrix.service.PutNumbersInMainDiagonal;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.PutterThread;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Deque;
import java.util.concurrent.TimeUnit;

public class ThreadWithDeque extends PutterThread {
    Deque<Integer> indexes;
    Logger logger = (Logger) LogManager.getLogger(ThreadWithDeque.class);

    public ThreadWithDeque(Matrix matrix, Deque<Integer> indexes, int number) {
        super(matrix, number);
        this.indexes = indexes;
    }

    @Override
    public void run() {
        try {
            while (!indexes.isEmpty()) {
                matrix.put(indexes.getFirst(), indexes.removeFirst(), number);
                PutNumbersInMainDiagonal.inc();
                String logInfo = getName() + " put " + number;
                logger.log(Level.INFO, logInfo);
                TimeUnit.MILLISECONDS.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
