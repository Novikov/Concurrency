package count_down_latch;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch - удобный механизм позволяющий убедиться в том, что все потоки, в которых выполняется некая работа, -
 * завершились.
 * */

public class Program {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Work(countDownLatch);
        new Work(countDownLatch);
        new Work(countDownLatch);

        countDownLatch.await(); //Приостанавляваем главный поток.

        System.out.println("All job is done");

    }

    static class Work extends Thread{
        CountDownLatch countDownLatch;

        public Work(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
            this.start();
        }

        @Override
        public void run() {
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("work is done");
            countDownLatch.countDown();
        }
    }
}
