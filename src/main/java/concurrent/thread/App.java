package concurrent.thread;

class ThreadMain4 extends App {
    public static void main(String[] args) throws InterruptedException {
        ThreadMain4 ob = new ThreadMain4();

        Thread t = ob.thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("New thread running.");

                Thread.sleep(1000);
                System.out.println("Still running.");

                Thread.sleep(1000);
                System.out.println("Completed.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t.join();
        System.out.println("New thread joined.");
    }
}

public class App {
    public Thread thread(Runnable body) {
        Thread t = new Thread(body);
        t.start();
        return t;
    }
}