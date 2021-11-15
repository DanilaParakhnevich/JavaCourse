package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.exception.PersistentException;

public interface TransactionFactory {
    public Transaction createTransaction();

    public void close() throws PersistentException;
}
