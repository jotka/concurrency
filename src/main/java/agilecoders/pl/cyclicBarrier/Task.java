/*
 * Copyright (c) 2013. agilecoders.pl
 */

package agilecoders.pl.cyclicBarrier;

import org.slf4j.Logger;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created with IntelliJ IDEA.
 * User: jarek
 * Date: 13.01.13
 * Time: 14:42
 */
public class Task implements Runnable {

    private CyclicBarrier barrier;
    private final Logger logger;

    public Task(CyclicBarrier barrier, Logger logger) {
        this.barrier = barrier;
        this.logger = logger;
    }

    @Override
    public void run() {
        try {
            logger.info(Thread.currentThread().getName() + " is waiting on barrier");
            barrier.await();
            logger.info(Thread.currentThread().getName() + " has crossed the barrier");
        } catch (InterruptedException ex) {
            logger.error("Task run InterruptedException ", ex);
        } catch (BrokenBarrierException ex) {
            logger.error("Task run BrokenBarrierException", ex);
        }
    }
}
