package by.parakhnevich.multithreadingmatrix.service.creators;

import by.parakhnevich.multithreadingmatrix.bean.Matrix;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.PutterThread;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.simplethread.SimpleThreadWithBoolean;

import java.util.ArrayList;
import java.util.List;

public class SimpleThreadsCreator {
    public List<PutterThread> create (List<Integer> listOfNumbers, Matrix matrix){
        List<PutterThread> list = new ArrayList<>();
        Boolean flag = false;
        for (Integer number : listOfNumbers) {
            list.add(new SimpleThreadWithBoolean(matrix, flag, number));
        }
        return list;
    }
}
