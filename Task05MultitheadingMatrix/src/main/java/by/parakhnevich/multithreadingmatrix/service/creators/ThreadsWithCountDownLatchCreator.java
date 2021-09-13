package by.parakhnevich.multithreadingmatrix.service.creators;

import by.parakhnevich.multithreadingmatrix.bean.Matrix;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.PutterThread;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.countdownlatchthread.ThreadWithCountDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ThreadsWithCountDownLatchCreator implements ThreadsCreator {
    public List<PutterThread> create (List<Integer> listOfNumbers, Matrix matrix) {
        List<PutterThread> list = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(matrix.getRows());
        for (Integer number : listOfNumbers) {
            list.add(new ThreadWithCountDownLatch(matrix, latch, number));
        }
        return list;
    }
}
