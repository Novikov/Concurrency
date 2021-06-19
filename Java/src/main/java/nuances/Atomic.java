package nuances;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {
    /**
     * Если использовать обычную переменную и инкрементировать ее из разных потоков, то она иногда может не проинкрементироваться.
     * Происходит наложение операций потоков.
     * Чтобы этого избежать нужно использовать атомарные переменные.
     * */
    static int i;

    static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 10_000; j++) {
            new MyThread().start();
        }
        Thread.sleep(2000);
        System.out.println(i);
        System.out.println(atomicInteger.get());
    }

    static class MyThread extends Thread{
        @Override
        public void run() {
            i++;
            atomicInteger.incrementAndGet();
        }
    }
}
