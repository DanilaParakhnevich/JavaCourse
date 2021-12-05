package by.parakhnevich.keddit.service.impl;

import by.parakhnevich.keddit.bean.publication.*;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.impl.TransactionFactoryImpl;
import by.parakhnevich.keddit.dao.interfaces.CommentDao;
import by.parakhnevich.keddit.dao.interfaces.RatingCommentDao;
import by.parakhnevich.keddit.dao.interfaces.Transaction;
import by.parakhnevich.keddit.dao.exception.DaoException;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.dao.exception.TransactionException;
import by.parakhnevich.keddit.service.interfaces.CommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private Transaction transaction = null;
    private TransactionFactoryImpl transactionFactory = null;
    Logger logger = LogManager.getLogger(CommentServiceImpl.class);


    @Override
    public List<Comment> selectByUser(User user) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            CommentDao commentDao = transaction.createDao(CommentDao.class);
            RatingCommentDao ratingCommentDao = transaction.createDao(RatingCommentDao.class);
            List<Comment> comments = commentDao.findCommentsByUserId(user.getId());
            for (Comment comment : comments) {
                comment.setUser(user);
                comment.setRatings(ratingCommentDao.getRatingsByComment(comment));
            }
            transactionFactory.close();
            return comments;
        } catch (TransactionException | DaoException | PersistentException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Comment> selectByPublication(Publication publication) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            CommentDao commentDao = transaction.createDao(CommentDao.class);
            RatingCommentDao ratingCommentDao = transaction.createDao(RatingCommentDao.class);
            List<Comment> comments = commentDao.findCommentsByPublicationId(publication.getId());
            for (Comment comment : comments) {
                comment.setUser(ServiceFactory.getInstance().getUserService().selectById(comment.getUser().getId()));
                comment.setRatings(ratingCommentDao.getRatingsByComment(comment));
            }
            transactionFactory.close();
            return comments;
        } catch (TransactionException | DaoException | PersistentException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public int getCountOfLikes(Comment comment) {
        int count = 0;
        for (Rating rating : comment.getRatings()) {
            if (rating.getClass() == Like.class) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int getCountOfDislikes(Comment comment) {
        int count = 0;
        for (Rating rating : comment.getRatings()) {
            if (rating.getClass() == Dislike.class) {
                count++;
            }
        }
        return count;
    }

    @Override
    public List<Comment> selectAll() throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            CommentDao commentDao = transaction.createDao(CommentDao.class);
            RatingCommentDao ratingCommentDao = transaction.createDao(RatingCommentDao.class);
            List<Comment> comments = commentDao.findAll();
            for (Comment comment : comments) {
                comment.setRatings(ratingCommentDao.getRatingsByComment(comment));
            }
            transactionFactory.close();
            return comments;
        } catch (TransactionException | DaoException | PersistentException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Comment selectById(long id) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            CommentDao commentDao = transaction.createDao(CommentDao.class);
            RatingCommentDao ratingCommentDao = transaction.createDao(RatingCommentDao.class);
            Comment comment = commentDao.findEntityById(id);
            comment.setRatings(ratingCommentDao.getRatingsByComment(comment));
            transactionFactory.close();
            return comment;
        } catch (TransactionException | DaoException | PersistentException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Comment update(Comment comment) throws ServiceException, PersistentException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            CommentDao commentDao = transaction.createDao(CommentDao.class);
            commentDao.update(comment);
            transaction.commit();
            transactionFactory.close();
            return comment;
        } catch (TransactionException | DaoException | PersistentException e) {
            transaction.rollback();
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Comment add(Comment comment) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Comment add(Comment comment, Publication publication) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            CommentDao commentDao = transaction.createDao(CommentDao.class);
            commentDao.createCommentByPublicationId(comment, publication.getId());
            transaction.commit();
            transactionFactory.close();
            return comment;
        } catch (TransactionException | DaoException | PersistentException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Comment delete(Comment comment) throws ServiceException, PersistentException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            CommentDao commentDao = transaction.createDao(CommentDao.class);
            RatingCommentDao ratingCommentDao = transaction.createDao(RatingCommentDao.class);
            for (int i = 0; i < comment.getCountOfRatings(); i++) {
                ratingCommentDao.deleteRatingByCommentId(comment.getId(), comment.getRating(i));
            }
            transaction.commit();
            commentDao.delete(comment);
            transaction.commit();
            transactionFactory.close();
            return comment;
        } catch (TransactionException | DaoException | PersistentException e) {
            transaction.rollback();
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public long getFreeId() throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            CommentDao commentDao = transaction.createDao(CommentDao.class);
            List<Comment> comments = commentDao.findAll();
            long highest = 1;
            for (Comment comment : comments) {
                if (comment.getId() > highest) {
                    highest = comment.getId();
                }
            }
            transactionFactory.close();
            return ++highest;
        } catch (PersistentException | TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }
}
