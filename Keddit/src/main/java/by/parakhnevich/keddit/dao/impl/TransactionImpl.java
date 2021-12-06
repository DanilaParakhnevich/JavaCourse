package by.parakhnevich.keddit.dao.impl;

import by.parakhnevich.keddit.dao.interfaces.BaseDao;
import by.parakhnevich.keddit.dao.interfaces.Transaction;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.dao.exception.TransactionException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The type Transaction.
 * @see by.parakhnevich.keddit.dao.interfaces.Transaction
 */
public class TransactionImpl implements Transaction {
    private static final String EXCEPTION_MESSAGE = "Unknown data service";
    private static final String PATH_TO_DAO = "by.parakhnevich.keddit.dao.interfaces.";
    private final Connection connection;

    /**
     * Instantiates a new Transaction.
     *
     * @param connection the connection
     * @throws SQLException the sql exception
     */
    public TransactionImpl(Connection connection) throws SQLException {
        this.connection = connection;
        connection.setAutoCommit(false);
    }

    /**
     * Creates a DAO-object instead of key
     *
     * @throws TransactionException the transaction exception
     */
    @Override
    public <T extends BaseDao> T createDao(Class<T> key) throws TransactionException {
        return switch (key.getName()) {
            case PATH_TO_DAO + "CommentDao" -> (T)new CommentDaoImpl(connection);
            case PATH_TO_DAO + "CommunityDao" -> (T)new CommunityDaoImpl(connection);
            case PATH_TO_DAO + "RatingCommentDao" -> (T)new RatingCommentDaoImpl(connection);
            case PATH_TO_DAO + "RatingPublicationDao" -> (T)new RatingPublicationDaoImpl(connection);
            case PATH_TO_DAO + "UserDao" -> (T)new UserDaoImpl(connection);
            case PATH_TO_DAO + "PublicationDao" -> (T)new PublicationDaoImpl(connection);
            default -> throw new TransactionException(EXCEPTION_MESSAGE);
        };
    }

    /**
     * Makes commit
     *
     * @throws PersistentException the persistent exception
     */
    @Override
    public void commit() throws PersistentException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }

    /**
     * Makes rollback
     *
     * @throws PersistentException the persistent exception
     */
    @Override
    public void rollback() throws PersistentException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new PersistentException(e);
        }
    }
}
