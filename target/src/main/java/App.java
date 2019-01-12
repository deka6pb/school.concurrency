public class App {
    public Thread thread(Runnable body) {
        Thread t = new Thread(body);
        t.start();
        return t;
    }
}

class ThreadSleep extends App {
    public static void main(String[] args) throws InterruptedException {
        ThreadSleep ob = new ThreadSleep();

        Thread t = ob.thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("New thread running.");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Still running.");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Completed.");
        });

        t.join();

        System.out.println("New thread joined.");
    }
}
