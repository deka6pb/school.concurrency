package concurrent.lock;

import concurrent.ConcurrentUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

/**
 * @author Benjamin Winterberg
 */
public class Lock6 {

    private static int count = 0;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        StampedLock lock = new StampedLock();

        executor.submit(() -> {
            long stamp = lock.readLock();
            System.out.println("Starting read lock stamp is " + stamp);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if (count == 0) {
                    System.out.println("Try converting lock");
//                    stamp = lock.tryConvertToWriteLock(stamp);
//                    if (stamp == 0L) {
                        System.out.println("Could not convert to write lock");
//                        lock.unlockRead(stamp);
                        stamp = lock.writeLock();
                        System.out.println("Write lock " + stamp);
//                    }
                    count = 23;
                }
                System.out.println(count);
            } finally {
                System.out.println("Finished read lock");
                lock.unlock(stamp);
            }
        });

        executor.submit(() -> {
            long stamp = lock.tryOptimisticRead();
            System.out.println("Starting read lock 2 stamp is " + stamp);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                System.out.println("Processing read lock 2");
            } finally {
                System.out.println("Finished read lock 2");
                lock.unlock(stamp);
            }
        });

        ConcurrentUtils.stop(executor);
    }
}
