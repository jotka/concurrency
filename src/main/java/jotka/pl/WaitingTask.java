package jotka.pl;

import org.slf4j.Logger;

import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 * User: jarek
 * Date: 13.01.13
 * Time: 11:56
 */
public class WaitingTask implements Runnable {
    private final CountDownLatch latch;
    private static int counter = 0;
    private static int id = counter++;
    private final Logger logger;

    public WaitingTask(CountDownLatch latch, Logger logger) {
        this.logger = logger;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            logger.info("after latch.await() in " + this);
        } catch (InterruptedException e) {
            logger.error("WaitingTask run", e);
        }
    }

    @Override
    public String toString() {
        return String.format("WaitingTask  %1$-3d", id);
    }
}
