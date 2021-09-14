package by.parakhnevich.multithreadingmatrix.service.threadformatrix.atomicthread;

import by.parakhnevich.multithreadingmatrix.bean.Matrix;
import by.parakhnevich.multithreadingmatrix.service.PutNumbersInMainDiagonal;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.PutterThread;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.countdownlatchthread.ThreadWithCountDownLatch;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadWithAtomic extends PutterThread {
    Logger logger = (Logger) LogManager.getLogger(ThreadWithAtomic.class);
    AtomicInteger index;

    public ThreadWithAtomic(Matrix matrix, AtomicInteger index, int number) {
        super(matrix, number);
        this.index = index;
    }

    @Override
    public void run() {
        while(true) {
            if (index.get() >= matrix.getRows()){
                break;
            }
            matrix.put(index.get(), index.getAndIncrement(), number);
            String logInfo = getName() + " put " + number;
            logger.log(Level.INFO, logInfo);
            PutNumbersInMainDiagonal.inc();
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextLong() % 50);
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
    }
}
