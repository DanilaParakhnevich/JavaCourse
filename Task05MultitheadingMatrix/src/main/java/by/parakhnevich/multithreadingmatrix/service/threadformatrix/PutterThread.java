package by.parakhnevich.multithreadingmatrix.service.threadformatrix;

import by.parakhnevich.multithreadingmatrix.bean.Matrix;
import by.parakhnevich.multithreadingmatrix.service.PutNumbersInMainDiagonal;


public class PutterThread extends Thread{
    protected Matrix matrix;
    protected int number;

    public PutterThread(Matrix matrix, int number) {
        super();
        this.matrix = matrix;
        this.number = number;
    }

    @Override
    public void run() {
        matrix.put(PutNumbersInMainDiagonal.index, PutNumbersInMainDiagonal.index, number);
        PutNumbersInMainDiagonal.inc();
    }
}
