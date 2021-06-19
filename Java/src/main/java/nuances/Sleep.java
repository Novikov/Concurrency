package nuances;

public class Sleep {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();

        Thread.sleep(3000); //Перевести поток в состояние waiting/blocking на 3 секунды
        System.out.println("thread main");
    }

    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("thread 0");
        }
    }
}


