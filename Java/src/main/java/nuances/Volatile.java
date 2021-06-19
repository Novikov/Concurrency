package nuances;

public class Volatile {
    static volatile int i = 0;
    //Ключевое слово volitile сигнализирует о том, что не нужно кешировать переменную, а писать непосредственно в нее
    //Если убрать это слово то поток MyThreadRed не отработает т.к он будет думать что i=0;

    public static void main(String[] args) {
        new MyThreadRead().start();
        new MyThreadWrite().start();
    }

    static class MyThreadWrite extends Thread{
        @Override
        public void run() {
            while (i<5){
                System.out.println("increment i to "+(++i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class MyThreadRead extends Thread{
        @Override
        public void run() {
            int localVar = i;
            while (localVar<5){
                if (localVar!=i){
                    System.out.println("new value of i is " + i);
                    localVar = i;
                }
            }
        }
    }
}
