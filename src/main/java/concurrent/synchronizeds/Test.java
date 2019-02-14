package concurrent.synchronizeds;

class App {
    public Thread thread(Runnable body) {
        Thread t = new Thread(body);
        t.start();
        return t;
    }
}

class Synchronized3 extends App {
    public static void main(String[] args) throws InterruptedException {

        Test test = new Test();

        Thread t = new Thread(test::getA);
        Thread t2 = new Thread(test::getB);

        t.start();
        t2.start();

        t.join();
        t2.join();
    }
}

class Test {
    public synchronized void getA() {
        try {
            System.out.println("Starting A");
            Thread.sleep(5000);
            System.out.println("Finished A");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void getB() {
        try {
            System.out.println("Starting B");
            Thread.sleep(1000);
            System.out.println("Finished B");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}