package by.parakhnevich.keddit.service.interfaces;

import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.dao.exception.TransactionException;

import java.util.List;

public interface Service <T>{
    public List<T> selectAll() throws TransactionException, ServiceException;

    public T selectById(long id) throws ServiceException;

    public T update(T t) throws ServiceException, PersistentException;

    public T add(T t) throws ServiceException;

    public T delete(T t) throws ServiceException, PersistentException;
}
