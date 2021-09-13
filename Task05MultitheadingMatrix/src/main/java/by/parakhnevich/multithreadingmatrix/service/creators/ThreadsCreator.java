package by.parakhnevich.multithreadingmatrix.service.creators;

import by.parakhnevich.multithreadingmatrix.bean.Matrix;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.PutterThread;

import java.util.List;

public interface ThreadsCreator {
    public List<PutterThread> create (List<Integer> listOfNumbers, Matrix matrix);
}
