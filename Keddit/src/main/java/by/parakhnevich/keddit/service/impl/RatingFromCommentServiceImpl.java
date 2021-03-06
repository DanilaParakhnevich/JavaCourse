package by.parakhnevich.keddit.service.impl;

import by.parakhnevich.keddit.bean.publication.Comment;
import by.parakhnevich.keddit.bean.publication.Rating;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.impl.TransactionFactoryImpl;
import by.parakhnevich.keddit.dao.interfaces.RatingCommentDao;
import by.parakhnevich.keddit.dao.interfaces.Transaction;
import by.parakhnevich.keddit.dao.exception.DaoException;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.dao.exception.TransactionException;
import by.parakhnevich.keddit.service.interfaces.CommentService;
import by.parakhnevich.keddit.service.interfaces.RatingFromCommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @see RatingFromCommentService
 */
public class RatingFromCommentServiceImpl implements RatingFromCommentService {
    private Transaction transaction = null;
    private TransactionFactoryImpl transactionFactory = null;

    @Override
    public List<Rating> selectByUser(User user) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            RatingCommentDao ratingCommentDao = transaction.createDao(RatingCommentDao.class);
            transactionFactory.close();
            return ratingCommentDao.findByUserId(user.getId());
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Rating> selectByComment(Comment comment) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            RatingCommentDao ratingCommentDao = transaction.createDao(RatingCommentDao.class);
            List<Rating> result = ratingCommentDao.getRatingsByComment(comment);
            transactionFactory.close();
            return result;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Rating add(Comment comment, Rating rating) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            RatingCommentDao ratingCommentDao = transaction.createDao(RatingCommentDao.class);
            ratingCommentDao.addRatingByCommentId(comment.getId(), rating);
            transaction.commit();
            transactionFactory.close();
            return rating;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Rating delete(Comment comment, Rating rating) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            RatingCommentDao ratingCommentDao = transaction.createDao(RatingCommentDao.class);
            ratingCommentDao.deleteRatingByCommentId(comment.getId(), rating);
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
            RatingCommentDao ratingCommentDao = transaction.createDao(RatingCommentDao.class);
            List<Rating> result = ratingCommentDao.findAll();
            transactionFactory.close();
            return result;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }
}
