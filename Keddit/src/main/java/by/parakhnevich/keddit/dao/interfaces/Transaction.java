package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.dao.exception.TransactionException;


/**
 * The interface Transaction.
 * @author Danila Parakhnevich
 */
public interface Transaction {
    <T extends BaseDao> T createDao(Class<T> key) throws TransactionException;
    void commit() throws PersistentException;
    void rollback() throws PersistentException;
}
