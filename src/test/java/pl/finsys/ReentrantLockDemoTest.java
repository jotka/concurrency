package pl.finsys;

import org.testng.annotations.Test;
import pl.finsys.lock.ReentrantLockDemo;

/**
 * Created with IntelliJ IDEA.
 * User: jaroslaw.krochmalski
 * Date: 07.05.13
 * Time: 09:51
 */
public class ReentrantLockDemoTest {
    @Test
    public void shouldUserLock() {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        demo.go();
    }
}
