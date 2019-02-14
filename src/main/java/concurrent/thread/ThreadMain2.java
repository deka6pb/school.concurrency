package concurrent.thread;

public class ThreadMain2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new MyThread(Thread.currentThread());

        t.start();
        t.join();
        System.out.println("New thread joined.");
    }
}

class MyThread extends Thread {
    private Thread t;

    public MyThread(Thread t) {
        this.t = t;
    }

    @Override
    public void run() {
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("New thread running.");
    }
}
