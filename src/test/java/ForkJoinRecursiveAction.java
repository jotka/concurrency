/*
 * Copyright (c) 2013. agilecoders.pl
 */

import agilecoders.pl.forkJoin.SplitRecursuveAction;
import com.google.common.base.Stopwatch;
import org.fest.util.Arrays;
import org.testng.annotations.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * (c) 2013 agilecoders.pl
 * User: jarek
 * Date: 07.04.13
 * Time: 07:46
 */
public class ForkJoinRecursiveAction {

    @Test
    public void shouldComputeVeryLargeArray() {

        int processors = Runtime.getRuntime().availableProcessors();
        Stopwatch stopWatch = new Stopwatch().start();

        ForkJoinPool pool = new ForkJoinPool(processors);

        Integer[] randomArray = Arrays.array(1, 2);

        SplitRecursuveAction action = new SplitRecursuveAction(randomArray);

        pool.invoke(action);

        stopWatch.stop();
        System.out.println("Elapsed Time: " + stopWatch.elapsedTime(TimeUnit.MILLISECONDS));
    }
}
