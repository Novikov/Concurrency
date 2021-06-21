package blocking_queue;

import java.util.PriorityQueue;
import java.util.Queue;


/**
 * При использовании такого кода может прилететь exception.
 * Т.к может произойти попытка получения элемента в пустой коллекции.
 * */
public class Incorrect {
    public static void main(String[] args) {
        Queue<String> queue = new PriorityQueue<>();

        new Thread(){
            @Override
            public void run() {
                System.out.println(queue.remove());
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
               queue.add("this is a string");
            }
        }.start();
    }
}
