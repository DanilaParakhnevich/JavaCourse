package by.parakhnevich.keddit.service.impl;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.impl.TransactionFactoryImpl;
import by.parakhnevich.keddit.dao.interfaces.*;
import by.parakhnevich.keddit.exception.DaoException;
import by.parakhnevich.keddit.exception.PersistentException;
import by.parakhnevich.keddit.exception.ServiceException;
import by.parakhnevich.keddit.exception.TransactionException;
import by.parakhnevich.keddit.service.interfaces.CommunityService;


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
            PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            UserDao userDao = transaction.createDao(UserDao.class);
            List<Publication> publications =
                    publicationDao.findPublicationsByCommunityId(community.getId());
            List<User> users = userDao.findUsersByFollowedCommunity(community);
            for (Publication publication : publications) {
                publication.setCommunityOwner(null);
                publicationDao.update(publication);
            }
            transaction.commit();
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
    public User addFollower(Community community, User user) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            communityDao.addFollower(community.getId(), user.getId());
            transactionFactory.close();
            return user;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User deleteFollower(Community community, User user) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            communityDao.deleteFollower(community.getId(), user.getId());
            transactionFactory.close();
            return user;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e);
        }
    }
}
