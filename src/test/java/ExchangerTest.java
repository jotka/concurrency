/*
 * Copyright (c) 2013. agilecoders.pl
 */

import agilecoders.pl.exchanger.ExchangerTask;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.fest.assertions.Assertions.assertThat;

/**
 * (c) 2013 agilecoders.pl
 * User: jarek
 * Date: 07.04.13
 * Time: 09:52
 */
public class ExchangerTest {
    @Test
    public void shoudlExchangeStringValue() {

        //given
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Exchanger<String> exchanger = new Exchanger<String>();

        ExchangerTask task1 = new ExchangerTask(exchanger, "task1");

        ExchangerTask task2 = new ExchangerTask(exchanger, "task2");

        //when


        List<Callable<Object>> todo = new ArrayList<Callable<Object>>(2);
        todo.add(Executors.callable(task1));
        todo.add(Executors.callable(task2));

        try {
            System.out.println("invoking all - start");
            executorService.invokeAll(todo);
            System.out.println("invoking all - stop");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //then
        assertThat(task1.getValue()).isEqualTo("task2");

        assertThat(task2.getValue()).isEqualTo("task1");


    }
}
