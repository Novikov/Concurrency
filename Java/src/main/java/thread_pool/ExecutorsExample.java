package thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 *Иногда возникает необходимость контролировать число новых потоков.
 *Огромное количество потоков может замедлить производительность.
 *ExecutorService ограничевает число потоков и решает данную проблему.
 */


public class ExecutorsExample {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 300; i++) {
            executorService.submit(new MyRunnable());
        }

        executorService.shutdown();

    }

    static class MyRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
