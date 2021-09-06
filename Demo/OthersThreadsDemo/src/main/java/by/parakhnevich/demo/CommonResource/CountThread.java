package by.parakhnevich.demo.CommonResource;

import java.util.concurrent.locks.ReentrantLock;

class CountThread implements Runnable {

    CommonResource res;
    ReentrantLock locker;

    CountThread(CommonResource res, ReentrantLock locker) {
        this.res = res;
        this.locker = locker;
    }

    public void run() {
        locker.lock();
        try {
            res.x = 1;
            for (int i = 1; i < 5; i++) {
                System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
                res.x++;
                Thread.sleep(100);
            }
        }
        catch(InterruptedException ignored){}
        finally {
            locker.unlock();
        }

    }
}

