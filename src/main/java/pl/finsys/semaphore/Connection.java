package pl.finsys.semaphore;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: jaroslaw.krochmalski
 * Date: 16.05.13
 * Time: 08:45
 */
public class Connection {
    private boolean available;

    public Connection() {
        this.available = true;
        log("created and available.");
    }

    public void doWork() throws InterruptedException {
        log("started.");
        TimeUnit.SECONDS.sleep(1);
        log("ended.");
    }

    public void aquire() {
        this.available = false;
        log("aquired.");
    }

    public void release() {
        this.available = true;
        log("released.");
    }

    private void log(String txt) {
        System.out.println(this + " " + txt);
    }

    public boolean isAvailable() {
        return available;
    }
}
