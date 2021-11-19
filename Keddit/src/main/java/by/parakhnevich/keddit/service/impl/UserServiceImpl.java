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
import by.parakhnevich.keddit.service.PasswordService;
import by.parakhnevich.keddit.service.interfaces.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private Transaction transaction = null;
    private TransactionFactoryImpl transactionFactory = null;
    PasswordService service = new PasswordService();


    @Override
    public List<User> selectAll() throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            final UserDao userDao = transaction.createDao(UserDao.class);
            final PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            final CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            List<User> users = userDao.findAll();
            for (User user : users) {
                user.setOwnCommunities(communityDao.getCommunitiesByUserId(user.getId()));
                user.setFollowingCommunities(communityDao.getFollowingCommunitiesByUserId(user.getId()));
                user.setPublications(publicationDao.findPublicationsByUserId(user.getId()));
            }
            transactionFactory.close();
            return users;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public User selectById(long id) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            final UserDao userDao = transaction.createDao(UserDao.class);
            final PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            final CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            User user = userDao.findEntityById(id);
            user.setOwnCommunities(communityDao.getCommunitiesByUserId(user.getId()));
            user.setFollowingCommunities(communityDao.getFollowingCommunitiesByUserId(user.getId()));
            user.setPublications(publicationDao.findPublicationsByUserId(user.getId()));
            transactionFactory.close();
            return user;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public User update(User user) throws ServiceException, PersistentException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            final UserDao userDao = transaction.createDao(UserDao.class);
            final PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            final CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            userDao.update(user);
            transaction.commit();
            for (int i = 0; i < user.countOfPublications(); i++) {
                publicationDao.update(user.getPublication(i));
            }
            transaction.commit();
            for (int i = 0; i < user.countOfFollowingCommunities(); i++) {
                communityDao.update(user.getFollowingCommunity(i));
            }
            transaction.commit();
            for (int i = 0; i < user.countOfOwnCommunities(); i++) {
                communityDao.update(user.getOwnCommunity(i));
            }
            transaction.commit();
            transactionFactory.close();
            return user;
        } catch (TransactionException | DaoException | PersistentException e) {
            transaction.rollback();
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public User add(User user) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
            final UserDao userDao = transaction.createDao(UserDao.class);
            userDao.create(user);
            transaction.commit();
            transactionFactory.close();
            return user;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public User delete(User user) throws ServiceException, PersistentException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
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
            transaction.commit();
            for (Publication publication : publications) {
                publicationDao.delete(publication);
            }
            transaction.commit();
            for (Comment comment : comments) {
                commentDao.delete(comment);
            }
            transaction.commit();
            for (Community community : ownCommunities) {
                communityDao.delete(community);
            }
            transaction.commit();
            userDao.delete(user);
            transaction.commit();
            transactionFactory.close();
            return user;
        } catch (TransactionException | DaoException | PersistentException e) {
            transaction.rollback();
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<User> selectByCommunity(Community community) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            this.transaction = transactionFactory.createTransaction();
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
            transactionFactory.close();
            return users;
        } catch (TransactionException | DaoException | PersistentException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isExist(String nickname, String password) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(UserDao.class);
            if (userDao.findUserByNickname(nickname) != null &&
                    service.isMatches(password, userDao.findUserByNickname(nickname).getPassword())) {
                return true;
            }
            transactionFactory.close();
            return false;
        } catch (PersistentException | TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User selectByMail(String mail) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(UserDao.class);
            PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            User user = userDao.findUserByMail(mail);
            if (user != null) {
                user.setOwnCommunities(communityDao.getCommunitiesByUserId(user.getId()));
                user.setFollowingCommunities(communityDao.getFollowingCommunitiesByUserId(user.getId()));
                user.setPublications(publicationDao.findPublicationsByUserId(user.getId()));
            }
            transactionFactory.close();
            return user;
        } catch (PersistentException | TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User selectByNickname(String nickname) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(UserDao.class);
            PublicationDao publicationDao = transaction.createDao(PublicationDao.class);
            CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            User user = userDao.findUserByNickname(nickname);
            if (user != null) {
                user.setOwnCommunities(communityDao.getCommunitiesByUserId(user.getId()));
                user.setFollowingCommunities(communityDao.getFollowingCommunitiesByUserId(user.getId()));
                user.setPublications(publicationDao.findPublicationsByUserId(user.getId()));
            }
            transactionFactory.close();
            return user;
        } catch (PersistentException | TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long getFreeId() throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            UserDao userDao = transaction.createDao(UserDao.class);
            List<User> users = userDao.findAll();
            long highest = 1;
            for (User user : users) {
                if (user.getId() > highest) {
                    highest = user.getId();
                }
            }
            transactionFactory.close();
            return ++highest;
        } catch (PersistentException | TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }
}
