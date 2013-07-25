package pl.finsys;/*
 * Copyright (c) 2013. finsys.pl
 */

import com.google.common.base.Stopwatch;
import org.testng.annotations.Test;
import pl.finsys.forkJoin.FibonacciProblem;
import pl.finsys.forkJoin.FibonacciTask;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * (c) 2013 finsys.pl
 * User: jarek
 * Date: 07.04.13
 * Time: 07:46
 */
public class ForkJoinRecursiveTaskTest {
    @Test
    public void testWithThreads() {

        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("No of processors: " + processors);

        int n = 10;

        Stopwatch stopWatch = new Stopwatch().start();
        FibonacciProblem bigProblem = new FibonacciProblem(n);

        FibonacciTask task = new FibonacciTask(bigProblem);
        ForkJoinPool pool = new ForkJoinPool(processors);

        pool.invoke(task);

        long result = task.result;
        System.out.println("Computed Result: " + result);

        stopWatch.stop();
        System.out.println("Elapsed Time: " + stopWatch.elapsedTime(TimeUnit.MILLISECONDS));

    }

    @Test
    public void testWithSingleThread() {
        int n = 10;

        Stopwatch stopWatch = new Stopwatch().start();

        FibonacciProblem bigProblem = new FibonacciProblem(n);

        long result = bigProblem.solve();
        stopWatch.stop();

        System.out.println("Computing Fib number: " + n);
        System.out.println("Computed Result: " + result);
        System.out.println("Elapsed Time: " + stopWatch.elapsedTime(TimeUnit.MILLISECONDS));
    }
}
