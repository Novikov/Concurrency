package nuances;

public class Yield {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();

        Thread.yield(); //уступить время для выполнения myThread. Но это не гарантируется.
        System.out.println("thread main");
    }

    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("thread 0");
        }
    }
}


