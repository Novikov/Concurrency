package exchanger;

import java.util.concurrent.Exchanger;

/**
 * Exchanger используется для обмена сообщениями между двумя потоками
 * */

public class Program {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Worker1(exchanger);
        new Worker2(exchanger);
    }

    static class Worker1 extends Thread{
        Exchanger<String> exchanger;

        public Worker1(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
            start();
        }

        @Override
        public void run() {
            try {
                exchanger.exchange("Hi, my name is Worker1");
                sleep(1000);
                exchanger.exchange("How are you?");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Worker2 extends Thread{
        Exchanger<String> exchanger;

        public Worker2(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
            start();
        }

        @Override
        public void run() {
            try {
                System.out.println(exchanger.exchange(null));
                System.out.println(exchanger.exchange(null));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
