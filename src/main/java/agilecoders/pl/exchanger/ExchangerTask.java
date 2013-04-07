/*
 * Copyright (c) 2013. agilecoders.pl
 */

package agilecoders.pl.exchanger;

import java.util.concurrent.Exchanger;

/**
 * (c) 2013 agilecoders.pl
 * User: jarek
 * Date: 07.04.13
 * Time: 09:48
 */
public class ExchangerTask implements Runnable {
    private Exchanger<String> exchanger;
    private String value;

    public ExchangerTask(Exchanger<String> exchanger, String value) {
        this.exchanger = exchanger;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void run() {
        try {
            Object previous = this.value;

            this.value = this.exchanger.exchange(this.value);

            System.out.println(Thread.currentThread().getName() + " exchanged " + previous + " for " + this.value);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
