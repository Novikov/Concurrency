package synchronization;

public class SynchronizedMethodExample {
    public static void main(String[] args) {
        Printer2 printer = new Printer2();
        CustomThread2 thread1 = new CustomThread2(printer,"Thread 1");
        CustomThread2 thread2 = new CustomThread2(printer,"Thread 2");
        thread1.start();
        thread2.start();
    }
}

class Printer2{
    synchronized void printPages(){
        for (int i=0;i<5;i++){
            System.out.println("Printing page - "+i+" from "+Thread.currentThread().getName());
        }
    }
}

class CustomThread2 extends Thread{
    Printer2 printer;
    String name;

    public CustomThread2(Printer2 printer, String name) {
        this.printer = printer;
        this.name = name;

    }
    @Override
    public void run() {
        Thread.currentThread().setName(name);
            printer.printPages();
    }
}
