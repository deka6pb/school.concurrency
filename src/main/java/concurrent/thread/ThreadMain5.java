package concurrent.thread;

public class ThreadMain5 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new MyTestThread();

        t.start();
        t.join();
        System.out.println("New thread joined.");
    }
}

class MyTestThread extends Thread {
    @Override
    public void run() {
        System.out.println("New thread running.");
    }
}