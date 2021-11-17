package by.parakhnevich.keddit.dao.impl;

import by.parakhnevich.keddit.connection.ConnectionPool;
import by.parakhnevich.keddit.dao.interfaces.Transaction;
import by.parakhnevich.keddit.dao.interfaces.TransactionFactory;
import by.parakhnevich.keddit.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactoryImpl implements TransactionFactory {
    Connection connection;
    private static final Logger logger = LogManager.getLogger(TransactionFactoryImpl.class);

    public TransactionFactoryImpl() throws PersistentException {
        connection = ConnectionPool.getConnectionPool().getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error(e);
            throw new PersistentException(e);
        }
    }

    @Override
    public Transaction createTransaction() throws PersistentException {
        try {
            return new TransactionImpl(connection);
        } catch (SQLException e) {
            logger.error(e);
            throw new PersistentException(e);
        }
    }

    @Override
    public void close() throws PersistentException {
        ConnectionPool.getConnectionPool().closeConnection(connection);
    }
}
