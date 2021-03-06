/*
 * Copyright (c) 2013. finsys.pl
 */

package pl.finsys.countdownLatch;

import org.slf4j.Logger;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: jarek
 * Date: 13.01.13
 * Time: 12:02
 */
public class TaskPortion implements Runnable {
    private final CountDownLatch latch;
    private static int counter = 0;
    private int id;
    private static Random rand = new Random(47);
    private final Logger logger;

    public TaskPortion(CountDownLatch latch, Logger logger) {
        this.logger = logger;
        this.latch = latch;
        this.id = counter++;
    }

    @Override
    public void run() {
        try {
            doWork();
            latch.countDown();
        } catch (InterruptedException e) {
            logger.error("TaskPortion run ", e);
        }
    }

    private void doWork() throws InterruptedException {
        logger.info("portion started " + this);
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
        logger.info("portion finished " + this);
    }

    @Override
    public String toString() {
        return String.format("portion %1$-3d", id);
    }
}
