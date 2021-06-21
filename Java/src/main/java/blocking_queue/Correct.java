package blocking_queue;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;


/**
 * C BlockingQueue всё работает.
 * */
public class Correct {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new PriorityBlockingQueue<>();

        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println(blockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                blockingQueue.add("this is a string");
            }
        }.start();
    }
}
