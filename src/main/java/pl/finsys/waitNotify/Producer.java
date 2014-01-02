package pl.finsys.waitNotify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;

/**
 * Place description here.
 *
 * @author jarek@finsys.pl
 */

public class Producer extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private final Queue sharedQueue;

    public Producer(Queue sharedQueue) {
        super("Producer");
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {

        for (int i = 0; i < 4; i++) {

            synchronized (sharedQueue) {
                while (sharedQueue.size() >= 1) { //waiting condition - wait until Queue is not empty
                    try {
                        logger.debug("Queue is full, waiting");
                        sharedQueue.wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                logger.debug("producing : " + i);
                sharedQueue.add(i);
                sharedQueue.notify();
            }
        }
    }
}

