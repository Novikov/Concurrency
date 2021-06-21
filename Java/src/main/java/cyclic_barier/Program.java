package cyclic_barier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * С помощью CyclicBarrier можно подождать готовности нескольких потоков.
 * Вызов метода CycliCbarrier.await() внутри потока - сигнал о готовности потока.
 * Как только будет 3 таких вызова потоки начнут работу.
 * Если закоментировать создание одного потока, то программа зависнет.
 * Нарратив для запоминания - гонка не начнется пока все бегуны не соберутся у линии старта.
 * */

public class Program {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,new Race());
        new Runner(cyclicBarrier,"Runner 1");
        new Runner(cyclicBarrier,"Runner 2");
        new Runner(cyclicBarrier,"Runner 3");
    }

    static class Race extends Thread{
        @Override
        public void run() {
            System.out.println("The race has begun");
        }
    }

    static class Runner extends Thread{
        CyclicBarrier cyclicBarrier;

        public Runner(CyclicBarrier cyclicBarrier,String name) {
            this.cyclicBarrier = cyclicBarrier;
            this.setName(name);
            start();
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
                System.out.println(this.getName()+" is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
