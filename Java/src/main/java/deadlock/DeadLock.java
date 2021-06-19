package deadlock;

public class DeadLock implements Runnable {
    A a = new A();
    B b = new B();

    DeadLock(){
        Thread.currentThread().setName("Главный поток");
        Thread t = new Thread(this,"Сопернячийпоток");
        t.start();
        a.foo(b);
        System.out.println("Назад в главный поток");
    }

    @Override
    public void run() {
        b.foo(a);
        System.out.println("Назад в другой поток");
    }

    public static void main(String[] args) {
        new DeadLock();
    }
}

class A{
    synchronized void foo(B b){
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " вошел в метод A.foo()");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Класс A прерван");
        }
        System.out.println(threadName + "пытается вызвать метод B.last()");
        b.last();
    }

    synchronized void last(){
        System.out.println("В методе A.last()");
    }
}

class B{
    synchronized void foo(A a){
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " вошел в метод B.bar()");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Класс B прерван");
        }
        System.out.println(threadName + "пытается вызвать метод A.last()");
        a.last();
    }

    synchronized void last(){
        System.out.println("В методе B.last()");
    }
}
