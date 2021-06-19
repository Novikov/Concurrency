package nuances;

public class Join {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();

        myThread.join(); //Заставить подождать scheduler выполнения текущего потока, а затем продолжить работу
        System.out.println("thread main");
    }

    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("thread 0");
        }
    }
}


