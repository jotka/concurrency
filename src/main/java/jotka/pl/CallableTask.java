package jotka.pl;

import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * User: jarek
 * Date: 13.01.13
 * Time: 17:37
 */
public class CallableTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 1;
    }
}
