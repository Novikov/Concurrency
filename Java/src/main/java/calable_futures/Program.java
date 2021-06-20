package calable_futures;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Program {

    /**
     * Позволяет выполнить работу в отдельном потоке и вернуть результат.
     * FutureTask имплементит runnable поэтому мы можем засунуть его в конструктор Thread.
     * */

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = new MyCallable();
        FutureTask futureTask = new FutureTask(callable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }

    static class MyCallable implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            int j = 0;
            for (int i = 0; i < 10; i++,j++) {
                Thread.sleep(500);
            }
            return j;
        }
    }
}
