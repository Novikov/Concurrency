package synchronization.conditions;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * В данном классе реализуется функциональность банковского счета.
 * Когда 2 потока пишут в переменную - есть вероятность ошибок начислений.
 * Мы можем уйти в минуса например.
 * Чтобы решить данную проблему можно использовать синхронизацию.
 * Но здесь мы используем condition для более гибкой синхронизации с помощью ReentrantLock.
 * */

public class Conditions {
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    static int account = 0;

    public static void main(String[] args) {
        new AccountMinus().start();
        new AccountPlus().start();
    }

    static class AccountPlus extends Thread{
        @Override
        public void run() {
            lock.lock();
            account+=10;
            condition.signal();
            lock.unlock();
        }
    }

    static class AccountMinus extends Thread{
        @Override
        public void run() {
            if (account<10){
                try {
                    lock.lock();
                    System.out.println("account="+account);
                    condition.await();
                    System.out.println("account="+account);
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
