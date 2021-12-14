package by.parakhnevich.keddit.dao.connection;

import by.parakhnevich.keddit.dao.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class is a data access pattern, whose main
 * purpose is to reduce the overhead involved in performing
 * database connections and read/write database operations.
 *
 * @author Danila Parakhnevich
 * @see by.parakhnevich.keddit.dao.impl.TransactionFactoryImpl
 */
public class ConnectionPool {
    private static ConnectionPool connectionPool;
    private Integer maxSize;
    private static final String BASIC_SQL_REQUEST = "select 1";
    private String url;
    private String username;
    private String password;
    private String driverName;
    private final BlockingQueue<Connection> freeConnections;
    private final BlockingQueue<Connection> usedConnections;
    private static final String CONNECTIONS_LIMIT = "The limit of number" +
            " of database connections is exceeded";
    private static final String IMPOSSIBLE_CONNECTION = "It's impossible to execute connection";
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static final Lock locker = new ReentrantLock();

    private ConnectionPool() throws PersistentException {
        ClassLoader classLoader = getClass().getClassLoader();
        Properties dbProperties = new Properties();
        String pathToProperties = "db.properties";
        InputStream path = classLoader.getResourceAsStream(pathToProperties);
        try {
            dbProperties.load(path);
            this.maxSize = Integer.parseInt(dbProperties.getProperty("maxSize"));
            this.url = dbProperties.getProperty("db.url");
            this.username = dbProperties.getProperty("db.username");
            this.password = dbProperties.getProperty("db.password");
            this.driverName = dbProperties.getProperty("db.driver");
        } catch (IOException e) {
            logger.error(e);
        }
        freeConnections = new ArrayBlockingQueue<>(maxSize);
        usedConnections = new ArrayBlockingQueue<>(maxSize);
        initPool();
    }

    /**
     * Gets connection pool.
     *
     * @return the connection pool
     * @throws PersistentException the persistent exception
     */
    public static ConnectionPool getConnectionPool() throws PersistentException {
        locker.lock();
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
            return connectionPool;
        }
        locker.unlock();
        return connectionPool;
    }

    /**
     * Gets connection.
     * If all the connections are used throw exception (look below)
     *
     * @return the connection
     * @throws PersistentException the persistent exception
     */
    public Connection getConnection() throws PersistentException {
        Connection connection = null;
        locker.lock();
        while (connection == null) {
            try {
                if (!freeConnections.isEmpty()) {
                    connection = freeConnections.take();
                    if (connection.isClosed()) {
                        makeAvailable(connection);
                    }
                } else if (usedConnections.size() < maxSize) {
                    connection = createNewConnection();
                } else {
                    logger.error(CONNECTIONS_LIMIT);
                    throw new PersistentException(CONNECTIONS_LIMIT);
                }
            } catch (SQLException | InterruptedException e) {
                logger.error(IMPOSSIBLE_CONNECTION);
                throw new PersistentException(IMPOSSIBLE_CONNECTION);
            } finally {
                locker.unlock();
            }
        }
        usedConnections.add(connection);
        return connection;
    }

    /**
     * Close connection.
     *
     * @param connection the connection
     */
    public void closeConnection(Connection connection) {
        if (connection != null && usedConnections.remove(connection)) {
            freeConnections.add(connection);
        }
    }

    private Connection createNewConnection() throws PersistentException {
        try {
            Class.forName(driverName);
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new PersistentException(e);
        }
    }

    private void makeAvailable(Connection connection) throws PersistentException {
        try {
            if (isConnectionAvailable(connection)) {
                return;
            }
            if (usedConnections.remove(connection)) {
                connection.close();
                connection = createNewConnection();
                usedConnections.add(connection);
            }
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    /**
     * Init pool.
     *
     * @throws PersistentException the persistent exception
     */
    public void initPool() throws PersistentException {
        destroy();
        for (int i = 0; i < maxSize; i++) {
            freeConnections.add(createNewConnection());
        }
    }

    /**
     * Destroy.
     */
    public void destroy() {
        usedConnections.addAll(freeConnections);
        freeConnections.clear();
        for (Connection connection : usedConnections) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        usedConnections.clear();
    }

    private boolean isConnectionAvailable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(BASIC_SQL_REQUEST);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
