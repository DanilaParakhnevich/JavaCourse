package by.parakhnevich.multithreadingmatrix.service.creators;

import by.parakhnevich.multithreadingmatrix.bean.Matrix;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.PutterThread;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.lockthread.ThreadWithLock;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadsWithLockCreator implements ThreadsCreator{
    public List<PutterThread> create (List<Integer> listOfNumbers, Matrix matrix){
        List<PutterThread> list = new ArrayList<>();
        ReentrantLock locker = new ReentrantLock(false);
        for (Integer number : listOfNumbers) {
            list.add(new ThreadWithLock(matrix, locker, number));
        }
        return list;
    }
}
