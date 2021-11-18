package by.parakhnevich.keddit.connection;

import by.parakhnevich.keddit.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {
    private static ConnectionPool connectionPool;
    private final Integer maxSize;
    private static final String BASIC_SQL_REQUEST = "select 1";
    private final String url;
    private final String username;
    private final String password;
    private final String driverName;
    private final LinkedBlockingDeque<Connection> freeConnections;
    private final LinkedBlockingDeque<Connection> usedConnections;
    private static final String CONNECTIONS_LIMIT = "The limit of number" +
            " of database connections is exceeded";
    private static final String IMPOSSIBLE_CONNECTION = "It's impossible to execute connection";
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

    private ConnectionPool() throws PersistentException {
        ClassLoader classLoader = getClass().getClassLoader();
        freeConnections = new LinkedBlockingDeque<>();
        usedConnections = new LinkedBlockingDeque<>();
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
            throw new PersistentException(e);
        }
        initPool();
    }

    public static ConnectionPool getConnectionPool() throws PersistentException {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }

    public Connection getConnection() throws PersistentException {
        Connection connection = null;
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
            }
        }
        usedConnections.add(connection);
        return connection;
    }

    public void closeConnection(Connection connection) throws PersistentException {
        try {
            if (isConnectionAvailable(connection)) {
                logger.info("Connection has already closed");
            } else if (usedConnections.contains(connection)) {
                usedConnections.remove(connection);
                connection.close();
                freeConnections.add(createNewConnection());
            }
            else {
                connection.close();
            }
        }
        catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    private Connection createNewConnection() {
        Connection connection = null;
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void makeAvailable(Connection connection) {
        if (isConnectionAvailable(connection)) {
            return;
        }
        usedConnections.remove(connection);
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = createNewConnection();
        usedConnections.add(connection);
    }

    public synchronized void initPool() {
        destroy();
        for (int i = 0; i < maxSize; i++) {
            freeConnections.add(createNewConnection());
        }
    }

    public synchronized void destroy() {
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
