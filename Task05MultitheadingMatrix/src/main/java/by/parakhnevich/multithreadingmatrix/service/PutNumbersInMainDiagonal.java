package by.parakhnevich.multithreadingmatrix.service;

import by.parakhnevich.multithreadingmatrix.service.threadformatrix.PutterThread;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class PutNumbersInMainDiagonal {
    public static int index = 0;

    public void execute(List<PutterThread> thread) throws InterruptedException {
        for (Thread thread1: thread){
            thread1.start();
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }

    public static void inc() {
        ++index;
    }

    public static void reset() {
        index = 0;
    }
}
