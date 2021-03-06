package by.parakhnevich.multithreadingmatrix.service.creators;

import by.parakhnevich.multithreadingmatrix.bean.Matrix;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.PutterThread;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.simplethread.SimpleThreadWithBoolean;

import java.util.ArrayList;
import java.util.List;

public class SimpleThreadsCreator implements ThreadsCreator{
    public List<PutterThread> create (List<Integer> listOfNumbers, Matrix matrix){
        List<PutterThread> list = new ArrayList<>();
        Boolean flag = false;// TODO: 15.09.2021 AtomicBoolean
        for (Integer number : listOfNumbers) {
            list.add(new SimpleThreadWithBoolean(matrix, flag, number));
        }
        return list;
    }
}
