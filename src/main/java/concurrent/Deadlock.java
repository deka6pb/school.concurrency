package concurrent;

class FirstClass {

    synchronized void foo(SecondClass b) {

        String name = Thread.currentThread().getName();
        System.out.println(name + " вошел в метод FirstClass.foo()");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Класс FirstClass прерван");
        }

        System.out.println(name + " пытается вызвать метод SecondClass.last()");
        b.last();
    }

    synchronized void last() {
        System.out.println("В методе FirstClass.last()");
    }
}

class SecondClass {

    synchronized void bar(FirstClass a) {

        String name = Thread.currentThread().getName();
        System.out.println(name + " вошел в метод SecondClass.bar()");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Класс SecondClass прерван");
        }

        System.out.println(name + " пытается вызвать метод FirstClass.last()");
        a.last();
    }

    synchronized void last() {
        System.out.println("В методе SecondClass.last()");
    }
}


class Deadlock implements Runnable {

    private FirstClass a = new FirstClass();
    private SecondClass b = new SecondClass();

    private Deadlock() {
        Thread.currentThread().setName("Главный поток");
        Thread t = new Thread(this, "Соперничающий поток");
        t.start();

        a.foo(b); // получить блокировку для объекта a
        // в этом потоке исполнения

        System.out.println("Назад в главный поток");
    }

    public void run() {
        b.bar(a); // получить блокировку для объекта b
        // в другом потоке исполнения
        System.out.println("Назад в другой поток");
    }

    public static void main(String args[]) {
        new Deadlock();
    }
}
