package by.parakhnevich.demo;

public class PriorThread extends Thread{
    public PriorThread(String name){
        super(name);
    }

    @Override
    public void run(){
        for (int i = 0; i < 50; i++) {
            System.out.println(getName() + " " + i);
        }
        try{
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PriorThread min = new PriorThread("Min");
        PriorThread max = new PriorThread("Max");
        PriorThread norm = new PriorThread("Norm");
        min.setPriority(MIN_PRIORITY);
        max.setPriority(MAX_PRIORITY);
        norm.setPriority(NORM_PRIORITY);
        min.start();
       // Thread.yield();
        norm.start();
       // Thread.yield();
        max.start();
    }
}
