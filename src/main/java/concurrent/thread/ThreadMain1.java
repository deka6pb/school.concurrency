package concurrent.thread;

public class ThreadMain1 {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        String name = t.getName();

        System.out.format("I'm the thread %s", name);
    }
}
