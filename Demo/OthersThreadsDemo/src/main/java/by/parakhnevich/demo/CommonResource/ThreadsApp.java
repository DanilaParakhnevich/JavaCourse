package by.parakhnevich.demo.CommonResource;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadsApp {

    public static void main(String[] args) {
        ReentrantLock locker = new ReentrantLock();
        CommonResource commonResource= new CommonResource();
        for (int i = 1; i < 6; i++){
            Thread t = new Thread(new CountThread(commonResource, locker));
            t.setName("Поток "+ i);
            t.start();
        }
    }
}

