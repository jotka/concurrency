/*
 * Copyright (c) 2013. finsys.pl
 */

package pl.finsys.callable;

import java.util.concurrent.Callable;

/**
 * (c) 2013 finsys.pl
 * User: jarek
 * Date: 17.03.13
 * Time: 09:39
 */

public class MyCallable implements Callable<Long> {
    @Override
    public Long call() throws Exception {
        long sum = 0;
        for (long i = 0; i <= 100; i++) {
            sum += i;
        }

        //sleep randomly
        Thread.sleep((long) (Math.random() * 1000));

        System.out.println("Thread " + Thread.currentThread().getId() + " finished.");
        return sum;
    }

}