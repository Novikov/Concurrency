package wait_notify;

public class Correct {
    public static void main(String[] args) {
        Q q = new Q();
        Producer producer = new Producer(q);
        Consumer consumer = new Consumer(q);
    }

    static class Q {
        int n;
        boolean valueSet = false;

        synchronized int get() {
            while (!valueSet) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("Исключение типа InterruptedException перехвачено");
                }
            }
            System.out.println("Получено "+n);
            valueSet = false;
            notify();
            return n;
        }

        synchronized void put(int n){
            while (valueSet) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("Исключение типа InterruptedException перехвачено");
                }
            }
            this.n = n;
            valueSet = true;
            System.out.println("Отправлено "+n);
            notify();
        }
    }

    static class Producer implements Runnable{
        Q q;

        public Producer(Q q) {
            this.q = q;
            new Thread(this, "Поставщик").start();
        }

        @Override
        public void run() {
            int i = 0;
            while(true){
                q.put(i++);
            }
        }
    }

    static class Consumer implements Runnable{
        Q q;

        public Consumer(Q q) {
            this.q = q;
            new Thread(this, "Потребитель").start();
        }

        @Override
        public void run() {
            while (true){
                q.get();
            }
        }
    }
}


