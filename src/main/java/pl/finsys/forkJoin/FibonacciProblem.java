/*
 * Copyright (c) 2013. finsys.pl
 */

package pl.finsys.forkJoin;

/**
 * (c) 2013 finsys.pl
 * User: jarek
 * Date: 07.04.13
 * Time: 07:42
 */

public class FibonacciProblem {

    public int n;

    public FibonacciProblem(int n) {
        this.n = n;
    }

    public long solve() {
        return fibonacci(n);
    }

    private long fibonacci(int n) {
        //System.out.println("Thread: " + Thread.currentThread().getName() + " calculates " + n);
        if (n <= 1)
            return n;
        else
            return fibonacci(n - 1) + fibonacci(n - 2);
    }

}

