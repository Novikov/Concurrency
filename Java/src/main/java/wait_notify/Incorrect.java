package wait_notify;

public class Incorrect {
    public static void main(String[] args) {
        Q q = new Q();
        Producer producer = new Producer(q);
        Consumer consumer = new Consumer(q);

        /**
         * Несмотря на то, что методы put() и get() синхронизированы в классе Q - ничто не остановит переполнение потребителя
         * данными от поставщика, так и ничто не помешает потребителю дважды извлечь один и тот же элемент из очереди.
         *
         * Т.е потоки соперничают за ресурс и пытаюстся как можно больше с ним работать.
         * */
    }

    static class Q {
        int n;

        synchronized int get() {
            System.out.println("Получено "+n);
            return n;
        }

        synchronized void put(int n){
            this.n = n;
            System.out.println("Отправлено "+n);
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


