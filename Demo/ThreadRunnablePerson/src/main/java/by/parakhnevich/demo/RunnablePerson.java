package by.parakhnevich.demo;

public class RunnablePerson extends Person implements Runnable{
    public RunnablePerson(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + ": Hello World");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RunnablePerson p1 = new RunnablePerson("Ann");
        Thread t1 = new Thread(p1);
        t1.start();
        System.out.println(t1.getState());
        RunnablePerson p2 = new RunnablePerson("Igor");
        Thread t2 = new Thread(p2);
        t2.start();
        t1.setPriority(10);
        System.out.println(t1.getState());
        t1.join();
        t2.yield();
        System.out.println(t1.getState());
    }
}
