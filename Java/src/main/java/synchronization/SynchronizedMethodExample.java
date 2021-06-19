package synchronization;

public class SynchronizedMethodExample {
    public static void main(String[] args) {
        Printer printer = new Printer();
        CustomThread thread1 = new CustomThread(printer,"Thread 1");
        CustomThread thread2 = new CustomThread(printer,"Thread 2");
        thread1.start();
        thread2.start();
    }

    static class Printer {
        synchronized void printPages(){
            for (int i=0;i<5;i++){
                System.out.println("Printing page - "+i+" from "+Thread.currentThread().getName());
            }
        }
    }

    static class CustomThread extends Thread{
        Printer printer;
        String name;

        public CustomThread(Printer printer, String name) {
            this.printer = printer;
            this.name = name;

        }
        @Override
        public void run() {
            Thread.currentThread().setName(name);
            printer.printPages();
        }
    }
}




