package by.parakhnevich.keddit.service.impl;

import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.publication.Rating;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.impl.TransactionFactoryImpl;
import by.parakhnevich.keddit.dao.interfaces.RatingPublicationDao;
import by.parakhnevich.keddit.dao.interfaces.Transaction;
import by.parakhnevich.keddit.exception.DaoException;
import by.parakhnevich.keddit.exception.PersistentException;
import by.parakhnevich.keddit.exception.ServiceException;
import by.parakhnevich.keddit.exception.TransactionException;
import by.parakhnevich.keddit.service.interfaces.RatingFromPublicationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RatingFromPublicationServiceImpl implements RatingFromPublicationService {
    Logger logger = LogManager.getLogger(RatingFromPublicationServiceImpl.class);
    Transaction transaction = null;

    public RatingFromPublicationServiceImpl() {
        try {
            transaction = new TransactionFactoryImpl().createTransaction();
        } catch (PersistentException e) {
            logger.error(e);
        }
    }

    @Override
    public List<Rating> selectByUser(User user) throws ServiceException {
        try {
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            return ratingPublicationDao.findByUserId(user.getId());
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Rating> selectByPublication(Publication publication) throws ServiceException {
        try {
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            return ratingPublicationDao.getRatingsByPublication(publication);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Rating add(Publication publication, Rating rating) throws ServiceException {
        try {
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            ratingPublicationDao.addRatingByPublicationId(publication.getId(), rating);
            return rating;
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Rating delete(Publication publication, Rating rating) throws ServiceException {
        try {
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            ratingPublicationDao
                    .deleteRatingByPublicationId(publication.getId(), rating);
            return rating;
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Rating> selectAll() throws ServiceException {
        try {
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            return ratingPublicationDao.findAll();
        } catch (TransactionException | DaoException e) {
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
