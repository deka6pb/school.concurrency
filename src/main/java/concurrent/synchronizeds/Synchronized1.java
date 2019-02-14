package concurrent.synchronizeds;

import concurrent.ConcurrentUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.stream.IntStream;

public class Synchronized1 {

    private static final int NUM_INCREMENTS = 10000;

    private static int count = 0;

    public static void main(String[] args) {
        testSyncIncrement();
//        testNonSyncIncrement();
    }

    private static void testSyncIncrement() {
        count = 0;

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, NUM_INCREMENTS)
                .parallel()
                .peek(i -> executor.submit(Synchronized1::incrementSync))
                .peek(i -> executor.submit(Synchronized1::increment2Sync))
                .forEach(i -> {});

        ConcurrentUtils.stop(executor);

        System.out.println("   Sync: " + count);
    }

//    private static void testNonSyncIncrement() {
//        count = 0;
//
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//
//        IntStream.range(0, NUM_INCREMENTS)
//                .forEach(i -> executor.submit(Synchronized1::increment));
//
//        ConcurrentUtils.stop(executor);
//
//        System.out.println("NonSync: " + count);
//    }

    private static synchronized void incrementSync() {
        count = count + 1;
    }

    private static synchronized void increment2Sync() {
        count = count + 1;
    }

    private static void increment() {
        count = count + 1;
    }
}
