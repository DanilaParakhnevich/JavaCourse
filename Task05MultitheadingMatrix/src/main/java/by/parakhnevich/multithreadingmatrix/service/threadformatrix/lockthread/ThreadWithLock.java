package by.parakhnevich.multithreadingmatrix.service.threadformatrix.lockthread;

import by.parakhnevich.multithreadingmatrix.bean.Matrix;
import by.parakhnevich.multithreadingmatrix.service.PutNumbersInMainDiagonal;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.PutterThread;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadWithLock extends PutterThread {
    ReentrantLock locker;
    Logger logger = (Logger) LogManager.getLogger(ThreadWithLock.class);

    public ThreadWithLock(Matrix matrix, ReentrantLock locker, int number) {
        super(matrix,number);
        this.locker = locker;
    }

    @Override
    public void run(){
       while (true) {
           try {
           locker.lock();
           if (PutNumbersInMainDiagonal.index >= matrix.getRows()) {
               break;
           }
           matrix.put(PutNumbersInMainDiagonal.index, PutNumbersInMainDiagonal.index, number);
           String logInfo = getName() + " put " + number;
           logger.log(Level.INFO, logInfo);
           PutNumbersInMainDiagonal.inc();
           locker.unlock();
               TimeUnit.MILLISECONDS.sleep(50);
           } catch (InterruptedException e) {
               logger.error(e);
           }
           finally {
               if (locker.isLocked()) {
                   locker.unlock();
               }
           }
       }
    }
}
