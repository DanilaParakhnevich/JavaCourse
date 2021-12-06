package by.parakhnevich.keddit.service.interfaces;

import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.dao.exception.TransactionException;

import java.util.List;


/**
 * The interface Service.
 *
 * @param <T> the type parameter
 * @author Danila Parakhnevich
 */
public interface Service <T>{
    /**
     * Select all list.
     *
     * @return the list
     * @throws TransactionException the transaction exception
     * @throws ServiceException     the service exception
     */
    List<T> selectAll() throws TransactionException, ServiceException;
}
