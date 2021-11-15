package by.parakhnevich.keddit.service.impl;

import by.parakhnevich.keddit.bean.publication.Comment;
import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.publication.Rating;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.impl.TransactionFactoryImpl;
import by.parakhnevich.keddit.dao.interfaces.CommentDao;
import by.parakhnevich.keddit.dao.interfaces.RatingCommentDao;
import by.parakhnevich.keddit.dao.interfaces.RatingDao;
import by.parakhnevich.keddit.dao.interfaces.Transaction;
import by.parakhnevich.keddit.exception.DaoException;
import by.parakhnevich.keddit.exception.PersistentException;
import by.parakhnevich.keddit.exception.ServiceException;
import by.parakhnevich.keddit.exception.TransactionException;
import by.parakhnevich.keddit.service.interfaces.CommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private Transaction transaction = null;
    Logger logger = LogManager.getLogger(CommentServiceImpl.class);

    public CommentServiceImpl() {
        try {
            transaction = new TransactionFactoryImpl().createTransaction();
        } catch (PersistentException e) {
            logger.error(e);
        }
    }

    @Override
    public List<Comment> selectByUser(User user) throws ServiceException {
        try {
            CommentDao commentDao = transaction.createDao(CommentDao.class);
            RatingCommentDao ratingCommentDao = transaction.createDao(RatingCommentDao.class);
            List<Comment> comments = commentDao.findCommentsByUserId(user.getId());
            for (Comment comment : comments) {
                comment.setRatings(ratingCommentDao.getRatingsByComment(comment));
            }
            return comments;
        } catch (TransactionException | DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Comment> selectByPublication(Publication publication) throws ServiceException {
        try {
            CommentDao commentDao = transaction.createDao(CommentDao.class);
            RatingCommentDao ratingCommentDao = transaction.createDao(RatingCommentDao.class);
            List<Comment> comments = commentDao.findCommentsByPublicationId(publication.getId());
            for (Comment comment : comments) {
                comment.setRatings(ratingCommentDao.getRatingsByComment(comment));
            }
            return comments;
        } catch (TransactionException | DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Rating addRating(Rating rating) throws ServiceException {
        try {
            RatingDao ratingDao = transaction.createDao(RatingDao.class);
            ratingDao.create(rating);
            return rating;
        } catch (TransactionException | DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Rating deleteRating(Rating rating) throws ServiceException {
        try {
            RatingDao ratingDao = transaction.createDao(RatingDao.class);
            ratingDao.delete(rating);
            return rating;
        } catch (TransactionException | DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Comment> selectAll() throws ServiceException {
        try {
            CommentDao commentDao = transaction.createDao(CommentDao.class);
            RatingCommentDao ratingCommentDao = transaction.createDao(RatingCommentDao.class);
            List<Comment> comments = commentDao.findAll();
            for (Comment comment : comments) {
                comment.setRatings(ratingCommentDao.getRatingsByComment(comment));
            }
            return comments;
        } catch (TransactionException | DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Comment selectById(long id) throws ServiceException {
        try {
            CommentDao commentDao = transaction.createDao(CommentDao.class);
            RatingCommentDao ratingCommentDao = transaction.createDao(RatingCommentDao.class);
            Comment comment = commentDao.findEntityById(id);
            comment.setRatings(ratingCommentDao.getRatingsByComment(comment));
            return comment;
        } catch (TransactionException | DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Comment update(Comment comment) throws ServiceException {
        try {
            CommentDao commentDao = transaction.createDao(CommentDao.class);
            RatingCommentDao ratingCommentDao = transaction.createDao(RatingCommentDao.class);
            commentDao.update(comment);
            for (int i = 0; i < comment.getCountOfRatings(); i++) {
                ratingCommentDao.update(comment.getRating(i));
            }
            return comment;
        } catch (TransactionException | DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Comment add(Comment comment) throws ServiceException {
        try {
            CommentDao commentDao = transaction.createDao(CommentDao.class);
            commentDao.create(comment);
            return comment;
        } catch (TransactionException | DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Comment delete(Comment comment) throws ServiceException {
        try {
            CommentDao commentDao = transaction.createDao(CommentDao.class);
            RatingCommentDao ratingCommentDao = transaction.createDao(RatingCommentDao.class);
            for (int i = 0; i < comment.getCountOfRatings(); i++) {
                ratingCommentDao.delete(comment.getRating(i));
            }
            commentDao.delete(comment);
            return comment;
        } catch (TransactionException | DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}
