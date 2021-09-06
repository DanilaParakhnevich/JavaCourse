package by.parakhnevich.demo;

public class MyThread extends Thread{
    @Override
    public void run(){
        System.out.println("Hello world!");
    }

    public static void main(String[] args) {
        new MyThread().start();
        new MyThread().start();
        new MyThread().start();
    }
}
