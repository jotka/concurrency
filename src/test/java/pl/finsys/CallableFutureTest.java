package pl.finsys;

import org.testng.annotations.Test;
import pl.finsys.callable.MyCallable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.fest.assertions.Fail.fail;
import static org.testng.Assert.assertTrue;

/**
 * Callable and Future test
 *
 * @author q1wk@nykredit.dk
 */

public class CallableFutureTest {

    private static final int NTHREDS = 10;
    private Long sum = 0l;

    @Test
    public void testFutures() {

        ExecutorService executorService = Executors.newFixedThreadPool(NTHREDS);
        List<Future<Long>> futuresList = new ArrayList<>();

        //given
        for (int i = 0; i < NTHREDS; i++) {
            Callable<Long> myCallable = new MyCallable();
            Future<Long> submit = executorService.submit(myCallable);
            futuresList.add(submit);
        }

        //when
        for (Future<Long> longFuture : futuresList) {
            try {
                sum += longFuture.get();
            } catch (InterruptedException e) {
                fail(e.getMessage());
            } catch (ExecutionException e) {
                fail(e.getMessage());
            }
        }

        //then
        assertTrue(sum == NTHREDS * 5050);
    }
}

