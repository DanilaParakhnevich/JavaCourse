package by.parakhnevich.demo.InterruptedThread;

public class InterruptedThread extends Thread{
    @Override
    public void run() {
        try{
            while(true) {
                System.out.println("LALA");
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptedThread thread = new InterruptedThread();
        thread.start();
        Thread.sleep(600);
        thread.interrupt();
    }
}
