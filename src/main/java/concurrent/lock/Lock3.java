package concurrent.lock;

import concurrent.ConcurrentUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Benjamin Winterberg
 */
public class Lock3 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Map<String, String> map = new HashMap<>();

        ReadWriteLock lock = new ReentrantReadWriteLock();

        executor.submit(() -> {
            lock.readLock().lock();
            try {
                System.out.println("Read lock");
                ConcurrentUtils.sleep(3);
                map.put("foo", "bar");
            } finally {
                lock.readLock().unlock();
            }
        });

        Runnable readTask = () -> {
            lock.writeLock().lock();
            try {
                System.out.println("Write lock");
                System.out.println(map.get("foo"));
                ConcurrentUtils.sleep(3);
            } finally {
                lock.writeLock().unlock();
            }
        };
        executor.submit(readTask);
        executor.submit(readTask);

        ConcurrentUtils.stop(executor);
    }

}
