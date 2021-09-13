package by.parakhnevich.multithreadingmatrix.service.creators;

import by.parakhnevich.multithreadingmatrix.bean.Matrix;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.PutterThread;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.dequethread.ThreadWithDeque;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ThreadsWithDequeCreator implements ThreadsCreator{
    @Override
    public List<PutterThread> create(List<Integer> listOfNumbers, Matrix matrix) {
        List<PutterThread> list = new ArrayList<>();
        Deque<Integer> indexes = createDequeOfIndexes(matrix);
        for (Integer number : listOfNumbers) {
            list.add(new ThreadWithDeque(matrix, (ArrayDeque<Integer>) indexes, number));
        }
        return list;
    }

    private Deque<Integer> createDequeOfIndexes(Matrix matrix) {
        Deque<Integer> indexes = new ArrayDeque<>();
        for (int i = 0; i < matrix.getRows(); ++i) {
            indexes.add(i);
        }
        return indexes;
    }
}
