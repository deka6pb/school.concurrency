package concurrent.cas;

import concurrent.ConcurrentUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Cas1 {
    private static final int NUM_INCREMENTS = 10000;

    public static void main(String[] args) {
        NonblockingCounter counter = new NonblockingCounter();

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> executor.submit(counter::increment));

        ConcurrentUtils.stop(executor);

        System.out.println(counter.getValue());
    }
}

class NonblockingCounter {
    private AtomicInteger value = new AtomicInteger(0);

    public int getValue() {
        return value.get();
    }

    public int increment() {
        int v;
        do {
            v = value.get();
        } while (!value.compareAndSet(v, v + 1));
        return v + 1;
    }
}
