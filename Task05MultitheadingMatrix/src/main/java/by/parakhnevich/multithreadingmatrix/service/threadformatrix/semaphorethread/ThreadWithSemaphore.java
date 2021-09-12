package by.parakhnevich.multithreadingmatrix.service.threadformatrix.semaphorethread;

import by.parakhnevich.multithreadingmatrix.bean.Matrix;
import by.parakhnevich.multithreadingmatrix.service.PutNumbersInMainDiagonal;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.PutterThread;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


public class ThreadWithSemaphore extends PutterThread {
    Semaphore sema;
    Logger logger = (Logger) LogManager.getLogger(ThreadWithSemaphore.class);

    public ThreadWithSemaphore(Matrix matrix, Semaphore semaphore, int number) {
        super(matrix, number);
        this.sema = semaphore;
    }

    @Override
    public void run(){
        while (true) {
            try {
                sema.acquire();
                if (PutNumbersInMainDiagonal.index >= matrix.getRows()) {
                    break;
                }
                matrix.put(PutNumbersInMainDiagonal.index, PutNumbersInMainDiagonal.index,
                        number);
                String logInfo = getName() + " put " + number;
                logger.log(Level.INFO, logInfo);
                PutNumbersInMainDiagonal.inc();
                sema.release();
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                logger.error(e);
            }
            finally {
                sema.release();
            }
        }
    }
}
