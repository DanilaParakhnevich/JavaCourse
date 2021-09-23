package by.parakhnevich.multithreadingmatrix.service;

import by.parakhnevich.multithreadingmatrix.service.threadformatrix.PutterThread;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class PutNumbersInMainDiagonal {
    private static AtomicInteger index = new AtomicInteger(0);

    public void execute(List<PutterThread> thread) throws InterruptedException {
        int number = 1;
        for (Thread thread1: thread){
            thread1.setName("Thread " + number++);
            thread1.start();
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }

    public static void inc() {
        index.incrementAndGet();
    }

    public static int getIndex(){
        return index.get();
    }

    public static void reset() {
        index.set(0);
    }
}
