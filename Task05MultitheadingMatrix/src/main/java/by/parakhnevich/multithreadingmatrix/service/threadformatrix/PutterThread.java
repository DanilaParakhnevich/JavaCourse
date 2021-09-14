package by.parakhnevich.multithreadingmatrix.service.threadformatrix;

import by.parakhnevich.multithreadingmatrix.bean.Matrix;
import by.parakhnevich.multithreadingmatrix.service.PutNumbersInMainDiagonal;

import java.util.Random;
import java.util.concurrent.TimeUnit;


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
        while(true) {
            if (PutNumbersInMainDiagonal.index <= matrix.getRows()){
                break;
            }
            matrix.put(PutNumbersInMainDiagonal.index, PutNumbersInMainDiagonal.index, number);
            PutNumbersInMainDiagonal.inc();
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextLong() % 50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
