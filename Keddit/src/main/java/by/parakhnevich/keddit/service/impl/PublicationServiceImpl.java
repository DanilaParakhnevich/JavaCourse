package by.parakhnevich.keddit.service.impl;

import by.parakhnevich.keddit.bean.publication.*;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.impl.TransactionFactoryImpl;
import by.parakhnevich.keddit.dao.interfaces.*;
import by.parakhnevich.keddit.dao.exception.DaoException;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.dao.exception.TransactionException;
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
            UserDao userDao = transaction.createDao(UserDao.class);
            CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            RatingPublicationDao ratingPublicationDao =
                    transaction.createDao(RatingPublicationDao.class);
            List<Publication> publications = publicationDao.findAll();
            for (Publication publication : publications) {
                publication.setUser(userDao.findEntityById(publication.getUser().getId()));
                publication.setRatings(ratingPublicationDao.getRatingsByPublication(publication));
                if (publication.getCommunityOwner() != null) {
                    publication.setCommunityOwner(communityDao.findEntityById(publication.getCommunityOwner().getId()));
                }
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
            transaction.commit();
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
            System.out.println(publication.getId());
            publicationDao.create(publication);
            System.out.println(22);
            transaction.commit();
            for (String tag : publication.getTags()) {
                publicationDao.createTag(publication.getId(), tag);
            }
            transaction.commit();
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
            for (int i = 0; i < publication.getRatings().size();i++) {
                ratingPublicationDao.delete(publication.getRatings().get(i));
            }//ratings of publication
            transaction.commit();
            for (int i = 0; i < publication.getComments().size(); i++) {
                Comment comment = publication.getComments().get(i);
                for (int j = 0; j < comment.getCountOfRatings(); j++) {
                    ratingCommentDao.delete(comment.getRating(i));
                }//delete ratings from comment
                commentDao.delete(publication.getComments().get(i));
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

    @Override
    public int getCountOfLikes(Publication publication) throws ServiceException {
        int count = 0;
        for (Rating rating : publication.getRatings()) {
            if (rating.getClass() == Like.class) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int getCountOfDislikes(Publication publication) throws ServiceException {
        int count = 0;
        for (Rating rating : publication.getRatings()) {
            if (rating.getClass() == Dislike.class) {
                count++;
            }
        }
        return count;
    }

    @Override
    public long getFreeId() throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            List<Long> ids = publicationDao.findAllIdOfPublications();
            transactionFactory.close();
            return ids.get(ids.size() - 1) + 1;
        } catch (PersistentException | TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

}
