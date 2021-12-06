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


import java.util.List;

public class PublicationServiceImpl implements PublicationService {
    private Transaction transaction = null;
    private TransactionFactoryImpl transactionFactory = null;

    @Override
    public List<Publication> selectAll() throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            List<Publication> publications = publicationDao.findAll();
            for (Publication publication : publications) {
                publication.setRatings(ratingPublicationDao.getRatingsByPublication(publication));
            }
            transactionFactory.close();
            return publications;
        }
        catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Publication selectById(long id) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            Publication publication = publicationDao.findEntityById(id);
            publication.setRatings(ratingPublicationDao.getRatingsByPublicationId(id));
            transactionFactory.close();
            return publication;
        }
        catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Publication update(Publication publication) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            Publication result = transaction.createDao(PublicationDao.class).update(publication);
            transactionFactory.close();
            return result;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Publication add(Publication publication) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            publicationDao.create(publication);
            transactionFactory.close();
            return publication;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Publication delete(Publication publication) throws ServiceException, PersistentException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            CommentDao commentDao = transaction.createDao(CommentDao.class);
            RatingCommentDao ratingCommentDao = transaction.createDao(RatingCommentDao.class);
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            for (int i = 0; i < publication.getCountOfRatings(); i++) {
                ratingPublicationDao.delete(publication.getRating(i));
            }//ratings of publication
            transaction.commit();
            for (int i = 0; i < publication.getCountOfComments(); i++) {
                Comment comment = publication.getComment(i);
                for (int j = 0; j < comment.getCountOfRatings(); j++) {
                    ratingCommentDao.delete(comment.getRating(i));
                }//delete ratings from comment
                commentDao.delete(publication.getComment(i));
            }//delete comments pf publication
            transaction.commit();
            publicationDao.delete(publication);
            transaction.commit();
            transactionFactory.close();
            return publication;
        } catch (TransactionException | DaoException | PersistentException e) {
            transaction.rollback();
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Publication> selectByUser(User user) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            List<Publication> publications =
                    publicationDao.findPublicationsByUserId(user.getId());
            for (Publication publication : publications) {
                publication.setRatings(ratingPublicationDao
                        .getRatingsByPublicationId(publication.getId()));
            }
            transactionFactory.close();
            return publications;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Publication> selectByCommunity(Community community) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            List<Publication> publications =
                    publicationDao.findPublicationsByCommunityId(community.getId());
            for (Publication publication : publications) {
                publication.setRatings(ratingPublicationDao
                        .getRatingsByPublicationId(publication.getId()));
            }
            transactionFactory.close();
            return publications;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }
}
