package by.parakhnevich.keddit.service.impl;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.impl.TransactionFactoryImpl;
import by.parakhnevich.keddit.dao.interfaces.*;
import by.parakhnevich.keddit.dao.exception.DaoException;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.dao.exception.TransactionException;
import by.parakhnevich.keddit.service.interfaces.CommunityService;
import by.parakhnevich.keddit.service.interfaces.PublicationService;
import by.parakhnevich.keddit.service.interfaces.RatingFromPublicationService;


import java.util.List;

public class CommunityServiceImpl implements CommunityService {
    private Transaction transaction = null;
    private TransactionFactoryImpl transactionFactory = null;

    @Override
    public List<Community> selectAll() throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            UserDao userDao = transaction.createDao(UserDao.class);
            PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            List<Community> communities = communityDao.findAll();
            for (Community community : communities) {
                User user = userDao.findEntityById(community.getUser().getId());
                user.setOwnCommunities(communityDao
                        .getCommunitiesByUserId(user.getId()));
                user.setFollowingCommunities(communityDao
                        .getFollowingCommunitiesByUserId(user.getId()));
                user.setPublications(publicationDao
                        .findPublicationsByUserId(user.getId()));
                community.setUser(user);
                community.setPublications(publicationDao
                        .findPublicationsByCommunityId(community.getId()));
                community.setFollowers(userDao
                        .findUsersByFollowedCommunity(community));
            }
            transactionFactory.close();
            return communities;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Community selectById(long id) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            UserDao userDao = transaction.createDao(UserDao.class);
            PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            Community community = communityDao.findEntityById(id);
            RatingFromPublicationService ratingFromPublicationService = new RatingFromPublicationServiceImpl();
            User user = userDao.findEntityById(community.getUser().getId());
            user.setOwnCommunities(communityDao
                    .getCommunitiesByUserId(user.getId()));
            user.setFollowingCommunities(communityDao
                    .getFollowingCommunitiesByUserId(user.getId()));
            user.setPublications(publicationDao
                    .findPublicationsByUserId(user.getId()));
            community.setUser(user);
            community.setPublications(publicationDao
                    .findPublicationsByCommunityId(community.getId()));
            for (Publication publication : community.getPublications()) {
                publication.setRatings(ratingFromPublicationService.selectByPublication(publication));
            }
            community.setFollowers(userDao
                    .findUsersByFollowedCommunity(community));

            transactionFactory.close();
            return community;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Community update(Community community) throws ServiceException, PersistentException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            UserDao userDao = transaction.createDao(UserDao.class);
            userDao.update(community.getUser());
            transaction.commit();
            communityDao.update(community);
            transaction.commit();
            transactionFactory.close();
            return community;
        } catch (TransactionException | DaoException | PersistentException e) {
            transaction.rollback();
            throw new ServiceException(e);
        }
    }

    @Override
    public Community add(Community community) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            communityDao.create(community);
            transaction.commit();
            transactionFactory.close();
            return community;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Community delete(Community community) throws ServiceException, PersistentException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            PublicationService publicationService = ServiceFactory.getInstance().getPublicationService();
            UserDao userDao = transaction.createDao(UserDao.class);
            List<Publication> publications =
                    publicationService.selectByCommunity(community);
            List<User> users = userDao.findUsersByFollowedCommunity(community);
            for (Publication publication : publications) {
                publicationService.delete(publication);
            }
            for (User user : users) {
                communityDao.deleteFollower(community.getId(), user.getId());
            }
            transaction.commit();
            communityDao.delete(community);
            transaction.commit();
            transactionFactory.close();
            return community;
        } catch (TransactionException | DaoException | PersistentException e) {
            transaction.rollback();
            throw new ServiceException(e);
        }
    }

    @Override
    public void addFollower(Community community, User user) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            communityDao.addFollower(community.getId(), user.getId());
            transaction.commit();
            transactionFactory.close();
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteFollower(Community community, User user) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            communityDao.deleteFollower(community.getId(), user.getId());
            transaction.commit();
            transactionFactory.close();
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getRating(Community community) throws ServiceException {
        PublicationService publicationService = new PublicationServiceImpl();
        int count = 0;
        for (Publication publication : community.getPublications()) {
            count += publicationService.getCountOfLikes(publication);
            count -= publicationService.getCountOfDislikes(publication);
        }
        return count;
    }

    @Override
    public List<Community> selectByUser(User user) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            UserDao userDao = transaction.createDao(UserDao.class);
            PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            List<Community> communities = communityDao.findAll();
            communities.removeIf(a -> a.getUser().getId() != user.getId());
            RatingFromPublicationService ratingFromPublicationService = new RatingFromPublicationServiceImpl();
            for (Community community : communities) {
                User user1 = userDao.findEntityById(community.getUser().getId());
                user1.setOwnCommunities(communityDao
                        .getCommunitiesByUserId(user1.getId()));
                user1.setFollowingCommunities(communityDao
                        .getFollowingCommunitiesByUserId(user1.getId()));
                user1.setPublications(publicationDao
                        .findPublicationsByUserId(user1.getId()));
                community.setUser(user1);
                community.setPublications(publicationDao
                        .findPublicationsByCommunityId(community.getId()));
                for (Publication publication : community.getPublications()) {
                    publication.setRatings(ratingFromPublicationService.selectByPublication(publication));
                }
                community.setFollowers(userDao
                        .findUsersByFollowedCommunity(community));
            }
            transactionFactory.close();
            return communities;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Community> selectByName(String name) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            List<Community> communities = communityDao.getCommunitiesByName(name);
            for (Community community : communities) {
                community = communityDao.findEntityById(community.getId());
            }
            transactionFactory.close();
            return communities;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long getFreeId() throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            List<Community> communities = communityDao.findAll();
            long highest = 1;
            for (Community community : communities) {
                if (community.getId() > highest) {
                    highest = community.getId();
                }
            }
            transactionFactory.close();
            return ++highest;
        } catch (PersistentException | TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }
}
