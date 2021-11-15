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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CommunityServiceImpl implements CommunityService {
    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);
    private Transaction transaction = null;

    public CommunityServiceImpl() {
        try {
            this.transaction = new TransactionFactoryImpl().createTransaction();
        } catch (PersistentException e) {
            logger.error(e);
        }
    }

    @Override
    public List<Community> selectAll() throws ServiceException {
        try {
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
            return communities;
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Community selectById(long id) throws ServiceException {
        try {
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
            return community;
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Community update(Community community) throws ServiceException {
        try {
            CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            UserDao userDao = transaction.createDao(UserDao.class);
            userDao.update(community.getUser());
            communityDao.update(community);
            return community;
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Community add(Community community) {
        return null;
    }

    @Override
    public Community delete(Community community) throws ServiceException {
        try {
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
            for (User user : users) {
                communityDao.deleteFollower(community.getId(), user.getId());
            }
            communityDao.delete(community);
            return community;
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User addFollower(Community community, User user) throws ServiceException {
        try {
            CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            communityDao.addFollower(community.getId(), user.getId());
            return user;
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User deleteFollower(Community community, User user) throws ServiceException {
        try {
            CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            communityDao.deleteFollower(community.getId(), user.getId());
            return user;
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }
}
