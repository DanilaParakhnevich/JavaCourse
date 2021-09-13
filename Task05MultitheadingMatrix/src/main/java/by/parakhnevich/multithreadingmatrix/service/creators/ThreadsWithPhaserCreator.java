package by.parakhnevich.multithreadingmatrix.service.creators;

import by.parakhnevich.multithreadingmatrix.bean.Matrix;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.PutterThread;
import by.parakhnevich.multithreadingmatrix.service.threadformatrix.phaserthread.ThreadWithPhaser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;

public class ThreadsWithPhaserCreator implements ThreadsCreator{
    public List<PutterThread> create (List<Integer> listOfNumbers, Matrix matrix) {
        List<PutterThread> list = new ArrayList<>();
        Phaser phaser = new Phaser();
        for (Integer number : listOfNumbers) {
            list.add(new ThreadWithPhaser(matrix, phaser, number));
        }
        return list;
    }
}
