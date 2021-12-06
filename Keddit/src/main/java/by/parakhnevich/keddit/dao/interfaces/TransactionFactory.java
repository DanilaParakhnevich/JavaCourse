package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.dao.exception.PersistentException;

/**
 * The interface Transaction factory.
 * @author Danila Parakhnevich
 */
public interface TransactionFactory {
    Transaction createTransaction() throws PersistentException;

    void close() throws PersistentException;
}
