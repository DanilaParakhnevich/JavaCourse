package by.parakhnevich.keddit.service.impl;

import by.parakhnevich.keddit.bean.publication.Comment;
import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.impl.TransactionFactoryImpl;
import by.parakhnevich.keddit.dao.interfaces.*;
import by.parakhnevich.keddit.exception.DaoException;
import by.parakhnevich.keddit.exception.PersistentException;
import by.parakhnevich.keddit.exception.ServiceException;
import by.parakhnevich.keddit.exception.TransactionException;
import by.parakhnevich.keddit.service.interfaces.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private Transaction transaction = null;
    Logger logger = LogManager.getLogger(UserServiceImpl.class);

    public UserServiceImpl() {
        try {
            transaction = new TransactionFactoryImpl().createTransaction();
        } catch (PersistentException e) {
            logger.error(e);
        }
    }

    @Override
    public List<User> selectAll() throws ServiceException {
        try {
            final UserDao userDao = transaction.createDao(UserDao.class);
            final PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            final CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            List<User> users = userDao.findAll();
            for (User user : users) {
                user.setOwnCommunities(communityDao.getCommunitiesByUserId(user.getId()));
                user.setFollowingCommunities(communityDao.getFollowingCommunitiesByUserId(user.getId()));
                user.setPublications(publicationDao.findPublicationsByUserId(user.getId()));
            }
            return users;
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public User selectById(long id) throws ServiceException {
        try {
            final UserDao userDao = transaction.createDao(UserDao.class);
            final PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            final CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            User user = userDao.findEntityById(id);
            user.setOwnCommunities(communityDao.getCommunitiesByUserId(user.getId()));
            user.setFollowingCommunities(communityDao.getFollowingCommunitiesByUserId(user.getId()));
            user.setPublications(publicationDao.findPublicationsByUserId(user.getId()));
            return user;
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public User update(User user) throws ServiceException {
        try {
            final UserDao userDao = transaction.createDao(UserDao.class);
            final PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            final CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            userDao.update(user);
            for (int i = 0; i < user.countOfPublications(); i++) {
                publicationDao.update(user.getPublication(i));
            }
            for (int i = 0; i < user.countOfFollowingCommunities(); i++) {
                communityDao.update(user.getFollowingCommunity(i));
            }
            for (int i = 0; i < user.countOfOwnCommunities(); i++) {
                communityDao.update(user.getOwnCommunity(i));
            }
            return user;
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public User add(User user) throws ServiceException {
        try {
            final UserDao userDao = transaction.createDao(UserDao.class);
            userDao.create(user);
            return user;
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public User delete(User user) throws ServiceException {
        try {
            final UserDao userDao = transaction.createDao(UserDao.class);
            final CommentDao commentDao = transaction.createDao(CommentDao.class);
            final PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            final CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            List<Comment> comments = commentDao.findCommentsByUserId(user.getId());
            List<Publication> publications =
                    publicationDao.findPublicationsByUserId(user.getId());
            List<Community> followingCommunities =
                    communityDao.getFollowingCommunitiesByUserId(user.getId());
            List<Community> ownCommunities = communityDao.getCommunitiesByUserId(user.getId());
            for (Community community : followingCommunities) {
                communityDao.deleteFollower(community.getId(), user.getId());
            }
            for (Publication publication : publications) {
                publicationDao.delete(publication);
            }
            for (Comment comment : comments) {
                commentDao.delete(comment);
            }
            for (Community community : ownCommunities) {
                communityDao.delete(community);
            }
            userDao.delete(user);
            return user;
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<User> selectByCommunity(Community community) throws ServiceException {
        try {
            final UserDao userDao = transaction.createDao(UserDao.class);
            final PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            final CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            List<User> users = userDao.findUsersByFollowedCommunity(community);
            for (User user : users) {
                user.setPublications(publicationDao.findPublicationsByUserId(user.getId()));
                user.setOwnCommunities(communityDao.getCommunitiesByUserId(user.getId()));
                user.setFollowingCommunities(communityDao.
                        getFollowingCommunitiesByUserId(user.getId()));
            }
            return users;
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
