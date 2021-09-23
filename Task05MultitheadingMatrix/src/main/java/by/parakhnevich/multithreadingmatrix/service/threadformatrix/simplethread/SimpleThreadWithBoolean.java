package by.parakhnevich.multithreadingmatrix.service.threadformatrix.simplethread;

import by.parakhnevich.multithreadingmatrix.bean.Matrix;
import by.parakhnevich.multithreadingmatrix.service.PutNumbersInMainDiagonal;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.PutterThread;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.concurrent.TimeUnit;


public class SimpleThreadWithBoolean extends PutterThread {
    Boolean isLocked;
    Logger logger = (Logger) LogManager.getLogger(SimpleThreadWithBoolean.class);

    public SimpleThreadWithBoolean(Matrix matrix, Boolean isLocked, int number) {
        super(matrix, number);
        this.isLocked = isLocked;
    }


    @Override
    public void run() {
        while (true) {
            try {
                if (!isLocked) {
                    isLocked = true;
                    if (PutNumbersInMainDiagonal.getIndex() >= matrix.getRows()) {
                        break;
                    }
                    matrix.put(PutNumbersInMainDiagonal.getIndex(), PutNumbersInMainDiagonal.getIndex(),
                            number);
                    String logInfo = getName() + " put " + number;
                    logger.log(Level.INFO, logInfo);
                    PutNumbersInMainDiagonal.inc();
                    isLocked = false;
                }
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                logger.error(e);
            } finally {
                isLocked = false;
            }
        }
    }
}
