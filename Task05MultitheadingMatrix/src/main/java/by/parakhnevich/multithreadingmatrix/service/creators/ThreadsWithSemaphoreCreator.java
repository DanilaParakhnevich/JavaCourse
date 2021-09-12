package by.parakhnevich.multithreadingmatrix.service.creators;

import by.parakhnevich.multithreadingmatrix.bean.Matrix;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.PutterThread;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.semaphorethread.ThreadWithSemaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;


public class ThreadsWithSemaphoreCreator {
    public List<PutterThread> create (List<Integer> listOfNumbers, Matrix matrix) {
        List<PutterThread> list = new ArrayList<>();
        Semaphore semaphore = new Semaphore(1);
        for (Integer number : listOfNumbers) {
            list.add(new ThreadWithSemaphore(matrix, semaphore, number));
        }
        return list;
    }
}
