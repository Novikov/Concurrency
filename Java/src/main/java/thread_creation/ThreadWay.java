package thread_creation;

import javax.print.attribute.standard.RequestingUserName;

public class ThreadWay {
    public static void main(String[] args) {
        //Запустится на фоновом потоке
        ThreadExample threadExample = new ThreadExample();
        threadExample.start();

        //Запустится на главном потоке
        RunnableExample runnableExample = new RunnableExample();
        runnableExample.run();

        //Запуск в фоновом потоке
        new Thread(runnableExample).start();
    }

    static class ThreadExample extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println("---"+Thread.currentThread().getName()+"---");
            for (int i=0;i<5;i++){
                System.out.println("work in ThreadExample");
            }
        }
    }

    static class RunnableExample implements Runnable{

        @Override
        public void run() {
            System.out.println("---"+Thread.currentThread().getName()+"---");
            for (int i=0;i<5;i++){
                System.out.println("work in RunnableExample");
            }
        }
    }
}




