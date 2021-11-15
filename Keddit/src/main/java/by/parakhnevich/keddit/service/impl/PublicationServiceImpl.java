package by.parakhnevich.keddit.service.impl;

import by.parakhnevich.keddit.bean.publication.Comment;
import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.publication.Rating;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.impl.TransactionFactoryImpl;
import by.parakhnevich.keddit.dao.interfaces.*;
import by.parakhnevich.keddit.exception.DaoException;
import by.parakhnevich.keddit.exception.PersistentException;
import by.parakhnevich.keddit.exception.ServiceException;
import by.parakhnevich.keddit.exception.TransactionException;
import by.parakhnevich.keddit.service.interfaces.PublicationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PublicationServiceImpl implements PublicationService {
    private static final Logger logger = LogManager.getLogger(PublicationServiceImpl.class);
    private Transaction transaction = null;

    public PublicationServiceImpl() {
        try {
            transaction = new TransactionFactoryImpl().createTransaction();
        } catch (PersistentException e) {
            logger.error(e);
        }
    }

    @Override
    public List<Publication> selectAll() throws ServiceException {
        try {
            PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            List<Publication> publications = publicationDao.findAll();
            for (Publication publication : publications) {
                publication.setRatings(ratingPublicationDao.getRatingsByPublication(publication));
            }
            return publications;
        }
        catch (TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Publication selectById(long id) throws ServiceException {
        try {
            PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            Publication publication = publicationDao.findEntityById(id);
            publication.setRatings(ratingPublicationDao.getRatingsByPublicationId(id));
            return publication;
        }
        catch (TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Publication update(Publication publication) throws ServiceException {
        try {
            return transaction.createDao(PublicationDao.class).update(publication);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Publication add(Publication publication) throws ServiceException {
        try {
            PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            publicationDao.create(publication);
            return publication;
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Publication delete(Publication publication) throws ServiceException {
        try {
            PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            CommentDao commentDao = transaction.createDao(CommentDao.class);
            RatingCommentDao ratingCommentDao = transaction.createDao(RatingCommentDao.class);
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            for (int i = 0; i < publication.getCountOfRatings(); i++) {
                ratingPublicationDao.delete(publication.getRating(i));
            }//ratings of publication
            for (int i = 0; i < publication.getCountOfComments(); i++) {
                Comment comment = publication.getComment(i);
                for (int j = 0; j < comment.getCountOfRatings(); j++) {
                    ratingCommentDao.delete(comment.getRating(i));
                }//delete ratings from comment
                commentDao.delete(publication.getComment(i));
            }//delete comments pf publication
            publicationDao.delete(publication);
            return publication;
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Publication> selectByUser(User user) throws ServiceException {
        try {
            PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            List<Publication> publications =
                    publicationDao.findPublicationsByUserId(user.getId());
            for (Publication publication : publications) {
                publication.setRatings(ratingPublicationDao
                        .getRatingsByPublicationId(publication.getId()));
            }
            return publications;
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Publication> selectByCommunity(Community community) throws ServiceException {
        try {
            PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            List<Publication> publications =
                    publicationDao.findPublicationsByCommunityId(community.getId());
            for (Publication publication : publications) {
                publication.setRatings(ratingPublicationDao
                        .getRatingsByPublicationId(publication.getId()));
            }
            return publications;
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Rating addRating(long id, Rating rating) throws ServiceException {
        try {
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            ratingPublicationDao.addRatingByPublicationId(id, rating);
            return rating;
        }
        catch (TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Rating deleteRating(long id, Rating rating) throws ServiceException {
        try {
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            ratingPublicationDao.deleteRatingByPublicationId(id, rating);
            return rating;
        }
        catch (TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }
}
