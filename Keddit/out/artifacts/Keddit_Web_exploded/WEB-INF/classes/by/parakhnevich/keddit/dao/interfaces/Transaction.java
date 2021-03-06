package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.bean.Entity;
import by.parakhnevich.keddit.exception.PersistentException;
import by.parakhnevich.keddit.exception.TransactionException;

public interface Transaction {
    <T extends BaseDao<?, ?>> T createDao(Class<T> key) throws TransactionException;
    public void commit() throws PersistentException;
    public void rollback() throws PersistentException;
}
