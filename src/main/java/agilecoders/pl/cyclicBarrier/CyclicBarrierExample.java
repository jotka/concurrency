/*
 * Copyright (c) 2013. agilecoders.pl
 */

package agilecoders.pl.cyclicBarrier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CyclicBarrier;

/**
 * Created with IntelliJ IDEA.
 * User: jarek
 * Date: 13.01.13
 * Time: 14:41
 */
public class CyclicBarrierExample {
    private static Logger logger = LoggerFactory.getLogger("CyclicBarrierExample");

    public static void main(String args[]) {

        //creating CyclicBarrier with 3 parties i.e. 3 Threads needs to call await()
        final CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                //This task will be executed once all thread reaches barrier
                logger.info("All parties are arrived at barrier, lets play");
            }
        });

        //starting each of thread
        Thread t1 = new Thread(new Task(cb, logger), "Thread 1");
        Thread t2 = new Thread(new Task(cb, logger), "Thread 2");
        Thread t3 = new Thread(new Task(cb, logger), "Thread 3");

        t1.start();
        t2.start();
        t3.start();

    }
}