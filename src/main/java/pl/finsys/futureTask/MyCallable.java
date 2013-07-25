package pl.finsys.futureTask;

import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * User: jarek
 * Date: 25.07.2013
 * Time: 23:19
 * To change this template use File | Settings | File Templates.
 */

public class MyCallable implements Callable<String> {

    private long waitTime;

    public MyCallable(int timeInMillis) {
        this.waitTime = timeInMillis;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(waitTime);
        //return the thread name executing this callable task
        return Thread.currentThread().getName();
    }

}