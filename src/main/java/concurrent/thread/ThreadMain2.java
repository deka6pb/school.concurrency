package concurrent.thread;

public class ThreadMain2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new MyThread();

        t.start();
        t.join();
        System.out.println("New thread joined.");
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("New thread running.");
    }
}