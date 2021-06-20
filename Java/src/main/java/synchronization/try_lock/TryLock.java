package synchronization.try_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLock {
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
    }

    static class Thread1 extends Thread{
        @Override
        public void run() {
            lock.lock();
            System.out.println("Thread_1 was started");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread_1 was stopped");
            lock.unlock();
        }
    }

    static class Thread2 extends Thread{
        @Override
        public void run() {
            System.out.println("Thread_2 was started");
            while (true){
                if (lock.tryLock()){
                    System.out.println("Thread_2 is working");
                    break;
                }
                else {
                    System.out.println("Thread_2 is waiting");
                }
            }
            System.out.println("Thread_2 was stopped");
        }
    }
}
