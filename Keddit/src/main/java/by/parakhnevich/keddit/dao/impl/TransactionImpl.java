package by.parakhnevich.keddit.dao.impl;

import by.parakhnevich.keddit.dao.interfaces.BaseDao;
import by.parakhnevich.keddit.dao.interfaces.Transaction;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.dao.exception.TransactionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionImpl implements Transaction {
    private static final Logger logger = LogManager.getLogger(TransactionImpl.class);
    private static final String EXCEPTION_MESSAGE = "Unknown data service";
    private static final String PATH_TO_DAO = "by.parakhnevich.keddit.dao.interfaces.";
    Connection connection;

    public TransactionImpl(Connection connection) throws SQLException {
        this.connection = connection;
        connection.setAutoCommit(false);
    }

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

    @Override
    public void commit() throws PersistentException {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error(e);
            throw new PersistentException(e);
        }
    }

    @Override
    public void rollback() throws PersistentException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error(e);
            throw new PersistentException(e);
        }
    }
}
