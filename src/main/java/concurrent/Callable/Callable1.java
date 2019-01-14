package concurrent.Callable;

import java.util.concurrent.*;

public class Callable1 {
    public static void main(String[] args) {
        Callable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return 123;
            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);

        System.out.println("future done? " + future.isDone());

//        executor.shutdownNow();

        Integer result = null;
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error");

            e.printStackTrace();
        }

        System.out.println("future done? " + future.isDone());
        System.out.print("result: " + result);
    }
}
