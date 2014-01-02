package pl.finsys;

import org.testng.annotations.Test;
import pl.finsys.waitNotify.Consumer;
import pl.finsys.waitNotify.Producer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Place description here.
 *
 * @author q1wk@nykredit.dk
 */
public class WaitNotifyTest {

    @Test
    public void runThreads() {

        final Queue sharedQ = new LinkedList();
        Thread producer = new Producer(sharedQ);
        Thread consumer = new Consumer(sharedQ);
        producer.start();
        consumer.start();
    }
}
