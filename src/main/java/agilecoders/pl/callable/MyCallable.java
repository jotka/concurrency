/*
 * Copyright (c) 2013. agilecoders.pl
 */

package agilecoders.pl.callable;

import java.util.concurrent.Callable;

/**
 * (c) 2013 agilecoders.pl
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
        return sum;
    }

}