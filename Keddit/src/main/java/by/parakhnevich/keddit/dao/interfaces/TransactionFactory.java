package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.dao.exception.PersistentException;

public interface TransactionFactory {
    public Transaction createTransaction() throws PersistentException;

    public void close() throws PersistentException;
}
