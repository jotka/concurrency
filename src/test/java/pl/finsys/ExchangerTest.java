package pl.finsys;/*
 * Copyright (c) 2013. finsys.pl
 */

import org.testng.annotations.Test;
import pl.finsys.exchanger.ExchangerTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.fest.assertions.Assertions.assertThat;

/**
 * (c) 2013 finsys.pl
 * User: jarek
 * Date: 07.04.13
 * Time: 09:52
 */
public class ExchangerTest {
    @Test
    public void shouldExchangeStringValue() {

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


    @Test
    public void shouldExchangeStringValueJoin() {

        //given
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Exchanger<String> exchanger = new Exchanger<String>();

        ExchangerTask task1 = new ExchangerTask(exchanger, "task1");

        ExchangerTask task2 = new ExchangerTask(exchanger, "task2");

        //when

        executorService.execute(task1);
        executorService.execute(task2);

        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //then
        assertThat(task1.getValue()).isEqualTo("task2");

        assertThat(task2.getValue()).isEqualTo("task1");


    }
}
