package pl.finsys.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA.
 * User: jaroslaw.krochmalski
 * Date: 16.05.13
 * Time: 08:46
 */
public class ConnectionPool {
    private final Semaphore semaphore;
    private List<Connection> connections;

    public ConnectionPool(int size) {
        System.out.println(String.format("initializing connection pool with %d connections", size));
        semaphore = new Semaphore(size);

        connections = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            connections.add(new Connection());
        }
    }

    public Connection aquire() throws Exception {
        semaphore.acquire();
        return firstAvailable();
    }

    private Connection firstAvailable() throws Exception {
        for (Connection connection : connections) {
            if (connection.isAvailable()) return connection;
        }
        throw new Exception("No available connections.");
    }

    public void release(Connection conn) {
        try {
            conn.release();
        } finally {
            semaphore.release();
        }
    }
}
