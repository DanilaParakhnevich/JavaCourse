package by.parakhnevich.demo.producerconsumer;

class Consumer extends Thread {
    Store store; // объект склада, с которого покупатель будет брать товар
    int product = 0; // текущее количество товаров со склада
    final int N = 5; // максимально допустимое число
    Consumer(Store store) {
        this.store = store;
    }

    public void run() {
        for (int i = 1; i < 6; i++) {
            store.get();
        }
    }

}

