package synchronization.rentrant_lock;

import java.util.concurrent.locks.ReentrantLock;

public class ProgramReentrantLock {
    public static void main(String[] args) throws InterruptedException {
        Resource resource = new Resource();
        MyThread myThread1 = new MyThread(resource);
        myThread1.setName("one");
        MyThread myThread2 = new MyThread(resource);
        myThread1.start();
        myThread2.start();
        myThread1.join();
        myThread2.join();
        System.out.println(resource.i);
    }

    static class Resource {
        int i = 0;

        ReentrantLock lock = new ReentrantLock();

        void changeI() {
            lock.lock();
            int i = this.i;
            if (Thread.currentThread().getName().equals("one")) {
                Thread.yield();
            }
            i++;
            this.i = i;
            lock.unlock();
        }
    }

    static class MyThread extends Thread {
        Resource resource;

        public MyThread(Resource resource) {
            this.resource = resource;
        }

        @Override
        public void run() {
            resource.changeI();
        }
    }

}
