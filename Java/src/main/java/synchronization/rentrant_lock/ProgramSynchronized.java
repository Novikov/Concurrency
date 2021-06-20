package synchronization.rentrant_lock;

public class ProgramSynchronized {
    public static void main(String[] args) throws InterruptedException {

        /**
         * Пример на синхронизацию. Если убрать synchronized у метода класса resource changeI() - то потоки могут некорректно изменять переменную.
         * Иногда она дважды проинкрементится, а иногда один.
         * */

        Resource resource = new Resource();
        resource.i = 5;
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
        int i;

        synchronized void changeI() {
            int i = this.i;
            if (Thread.currentThread().getName().equals("one")) {
                Thread.yield();
            }
            i++;
            this.i = i;
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
