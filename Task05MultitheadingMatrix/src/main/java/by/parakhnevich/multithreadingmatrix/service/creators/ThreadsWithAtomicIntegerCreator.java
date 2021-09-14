package by.parakhnevich.multithreadingmatrix.service.creators;

import by.parakhnevich.multithreadingmatrix.bean.Matrix;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.PutterThread;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.atomicthread.ThreadWithAtomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadsWithAtomicIntegerCreator implements ThreadsCreator{
    @Override
    public List<PutterThread> create(List<Integer> listOfNumbers, Matrix matrix) {
        List<PutterThread> list = new ArrayList<>();
        AtomicInteger index = new AtomicInteger(0);
        for (Integer number : listOfNumbers) {
            list.add(new ThreadWithAtomic(matrix, index, number));
        }
        return list;
    }
}
