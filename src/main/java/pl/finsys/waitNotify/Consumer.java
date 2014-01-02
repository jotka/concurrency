package pl.finsys.waitNotify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;

/**
 * Place description here.
 *
 * @author jarek@finsys.pl
 */

public class Consumer extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private final Queue sharedQueue;

    public Consumer(Queue sharedQueue) {
        super("Consumer");
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {

            synchronized (sharedQueue) {
                while (sharedQueue.size() == 0) {   //waiting condition - wait until Queue is not empty
                    try {
                        logger.debug("Queue is empty, waiting");
                        sharedQueue.wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                Object number = sharedQueue.poll();
                logger.debug("consuming : " + number);
                sharedQueue.notify();

                //termination condition
                if (number == 3) {
                    break;
                }
            }
        }
    }
}


