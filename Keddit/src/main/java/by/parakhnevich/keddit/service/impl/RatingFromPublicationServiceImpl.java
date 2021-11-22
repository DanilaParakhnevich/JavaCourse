package by.parakhnevich.keddit.service.impl;

import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.publication.Rating;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.impl.TransactionFactoryImpl;
import by.parakhnevich.keddit.dao.interfaces.RatingPublicationDao;
import by.parakhnevich.keddit.dao.interfaces.Transaction;
import by.parakhnevich.keddit.dao.exception.DaoException;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.dao.exception.TransactionException;
import by.parakhnevich.keddit.service.interfaces.RatingFromPublicationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RatingFromPublicationServiceImpl implements RatingFromPublicationService {
    Logger logger = LogManager.getLogger(RatingFromPublicationServiceImpl.class);
    Transaction transaction = null;
    TransactionFactoryImpl transactionFactory = null;

    @Override
    public List<Rating> selectByUser(User user) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            List<Rating> result = ratingPublicationDao.findByUserId(user.getId());
            transactionFactory.close();
            return result;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Rating> selectByPublication(Publication publication) throws ServiceException {
        try {
            this.transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            List<Rating> result = ratingPublicationDao.getRatingsByPublication(publication);
            this.transactionFactory.close();
            return result;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Rating add(Publication publication, Rating rating) throws ServiceException {
        try {
            this.transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            ratingPublicationDao.addRatingByPublicationId(publication.getId(), rating);
            transaction.commit();
            transactionFactory.close();
            return rating;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Rating delete(Publication publication, Rating rating) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            ratingPublicationDao
                    .deleteRatingByPublicationId(publication.getId(), rating);
            transaction.commit();
            transactionFactory.close();
            return rating;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Rating> selectAll() throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            List<Rating> result = ratingPublicationDao.findAll();
            transactionFactory.close();
            return result;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public Rating selectById(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Rating update(Rating rating) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Rating add(Rating rating) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Rating delete(Rating rating) {
        throw new UnsupportedOperationException();
    }
}
