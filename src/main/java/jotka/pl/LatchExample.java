package jotka.pl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: jarek
 * Date: 13.01.13
 * Time: 11:51
 */
public class LatchExample {
    private static Logger logger = LoggerFactory.getLogger("latchExample");

    public final static int SIZE = 100;

    public static void main(String[] args) {
        LatchExample example = new LatchExample();
        example.go();
    }

    private void go() {
        logger.info("start...");

        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(SIZE);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new WaitingTask(latch, logger));
        }
        for (int i = 0; i < SIZE; i++) {
            executorService.execute(new TaskPortion(latch, logger));
        }
        logger.info("finished all");
        executorService.shutdown();


    }

}
