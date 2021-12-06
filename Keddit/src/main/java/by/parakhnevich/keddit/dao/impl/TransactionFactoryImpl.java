package by.parakhnevich.keddit.dao.impl;

import by.parakhnevich.keddit.dao.connection.ConnectionPool;
import by.parakhnevich.keddit.dao.interfaces.Transaction;
import by.parakhnevich.keddit.dao.interfaces.TransactionFactory;
import by.parakhnevich.keddit.dao.exception.PersistentException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The type TransactionFactory.
 * @see TransactionFactory
 */
public class TransactionFactoryImpl implements TransactionFactory {
    private final Connection connection;

    /**
     * Instantiates a new Transaction factory
     * and creating connection
     *
     * @throws PersistentException the persistent exception
     */
    public TransactionFactoryImpl() throws PersistentException {
        connection = ConnectionPool.getConnectionPool().getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    /**
     * Creates transaction
     *
     * @throws PersistentException the persistent exception
     */
    @Override
    public Transaction createTransaction() throws PersistentException {
        try {
            return new TransactionImpl(connection);
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    /**
     * Closes connection
     *
     * @throws PersistentException the persistent exception
     */
    @Override
    public void close() throws PersistentException {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
        ConnectionPool.getConnectionPool().closeConnection(connection);
    }
}
