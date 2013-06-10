package pl.finsys;/*
 * Copyright (c) 2013. agilecoders.pl
 */

import com.google.common.base.Stopwatch;
import org.testng.annotations.Test;
import pl.finsys.forkJoin.FileSizeFinder;

import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * (c) 2013 agilecoders.pl
 * User: jarek
 * Date: 08.04.13
 * Time: 21:09
 */
public class FileSizeFinderTest {
    private File homeDirectory;

    @Test
    public void shoudCalculateFileSize() {
        Stopwatch stopWatch = new Stopwatch().start();
        int processors = Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = new ForkJoinPool(processors);

        final long total = pool.invoke(new FileSizeFinder(getHomeDirectory()));
        System.out.println("Total: " + total);
        stopWatch.stop();
        System.out.println("Elapsed Time: " + stopWatch.elapsedTime(TimeUnit.MILLISECONDS));
    }

    public File getHomeDirectory() {
        return new File(System.getProperty("user.home"));
    }
}
