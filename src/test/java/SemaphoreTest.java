import agilecoders.pl.semaphore.Connection;
import agilecoders.pl.semaphore.ConnectionPool;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.fest.assertions.Fail.fail;

/**
 * Created with IntelliJ IDEA.
 * User: jaroslaw.krochmalski
 * Date: 16.05.13
 * Time: 08:55
 */
public class SemaphoreTest {
    ConnectionPool pool;

    @BeforeTest
    public void beforeTest() {
        //given
        pool = new ConnectionPool(3);
    }

    @Test(threadPoolSize = 5, invocationCount = 40)
    public void shoudlWaitForConnection() {
        try {
            Connection connection = pool.aquire();
            connection.doWork();
            pool.release(connection);

        } catch (Exception exc) {
            fail(exc.getMessage());
        }
    }
}
