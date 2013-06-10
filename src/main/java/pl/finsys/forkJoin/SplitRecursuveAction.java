package pl.finsys.forkJoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * (c) 2013 finsys.pl
 * User: jarek
 * Date: 08.04.13
 * Time: 19:58
 */
public class SplitRecursuveAction extends RecursiveAction {
    private Integer[] list;
    public long result;

    public SplitRecursuveAction(Integer[] array) {
        this.list = array;
    }

    @Override
    protected void compute() {
        if (list.length == 1) {
            result = list[0];
        } else {
            int midpoint = list.length / 2;
            Integer[] l1 = Arrays.copyOfRange(list, 0, midpoint);
            Integer[] l2 = Arrays.copyOfRange(list, midpoint, list.length);
            SplitRecursuveAction action1 = new SplitRecursuveAction(l1);
            SplitRecursuveAction action2 = new SplitRecursuveAction(l2);
            invokeAll(action1, action2);
        }
    }
}
