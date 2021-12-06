package by.parakhnevich.keddit.service.impl;

import by.parakhnevich.keddit.bean.publication.*;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.impl.TransactionFactoryImpl;
import by.parakhnevich.keddit.dao.interfaces.*;
import by.parakhnevich.keddit.dao.exception.DaoException;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.dao.exception.TransactionException;
import by.parakhnevich.keddit.service.PasswordService;
import by.parakhnevich.keddit.service.interfaces.CommentService;
import by.parakhnevich.keddit.service.interfaces.CommunityService;
import by.parakhnevich.keddit.service.interfaces.PublicationService;
import by.parakhnevich.keddit.service.interfaces.UserService;

import java.util.List;

/**
 * @see UserService
 */
public class UserServiceImpl implements UserService {
    private Transaction transaction = null;
    private TransactionFactoryImpl transactionFactory = null;
    private final PasswordService service = new PasswordService();


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
            userDao.update(user);
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
            UserDao userDao = transaction.createDao(UserDao.class);
            PublicationService publicationService = ServiceFactory.getInstance().getPublicationService();
            CommunityService communityService = ServiceFactory.getInstance().getCommunityService();
            CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            List<Publication> publications =
                    publicationService.selectByUser(user);
            List<Community> followingCommunities =
                    communityDao.getFollowingCommunitiesByUserId(user.getId());
            List<Community> ownCommunities = communityDao.getCommunitiesByUserId(user.getId());
            for (Community community : followingCommunities) {
                communityService.deleteFollower(community, user);
            }
            for (Publication publication : publications) {
                publicationService.delete(publication);
            }
            for (Community community : ownCommunities) {
                communityService.delete(community);
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
                transactionFactory.close();
                return true;
            }
            transactionFactory.close();
            return false;
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

    @Override
    public int getCountOfLikes(User user) throws ServiceException {
        int count = 0;
        CommentService commentService = new CommentServiceImpl();
        PublicationService publicationService = new PublicationServiceImpl();
        for (Comment comment : commentService.selectByUser(user)) {
            for (Rating rating : comment.getRatings()) {
                if (rating.getClass() == Like.class) {
                    ++count;
                }
            }
        }
        for (Publication publication : publicationService.selectByUser(user)) {
            for (Rating rating : publication.getRatings()) {
                if (rating.getClass() == Like.class) {
                    ++count;
                }
            }
        }
        return count;
    }

    @Override
    public int getCountOfDislikes(User user) throws ServiceException {
        int count = 0;
        CommentService commentService = new CommentServiceImpl();
        PublicationService publicationService = new PublicationServiceImpl();
        for (Comment comment : commentService.selectByUser(user)) {
            for (Rating rating : comment.getRatings()) {
                if (rating.getClass() == Dislike.class) {
                    ++count;
                }
            }
        }
        for (Publication publication : publicationService.selectByUser(user)) {
            for (Rating rating : publication.getRatings()) {
                if (rating.getClass() == Dislike.class) {
                    ++count;
                }
            }
        }
        return count;
    }

    @Override
    public boolean hasLikedPublication(Publication publication, User user) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            RatingPublicationDao ratingPublicationDao = transaction.createDao(RatingPublicationDao.class);
            List<Rating> ratings = ratingPublicationDao.getRatingsByPublicationId(publication.getId());
            for (Rating rating : ratings) {
                if (rating.getClass() == Like.class && rating.getUser().getId() == user.getId()) {
                    transactionFactory.close();
                    return true;
                }
            }
            transactionFactory.close();
            return false;
        } catch (PersistentException | TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean hasLikedComment(Comment comment, User user) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            RatingCommentDao ratingCommentDao = transaction.createDao(RatingCommentDao.class);
            List<Rating> ratings = ratingCommentDao.getRatingsByCommentId(comment.getId());
            for (Rating rating : ratings) {
                if (rating.getClass() == Like.class && rating.getUser().getId() == user.getId()) {
                    transactionFactory.close();
                    return true;
                }
            }
            transactionFactory.close();
            return false;
        } catch (PersistentException | TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean hasDislikedPublication(Publication publication, User user) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            RatingPublicationDao ratingPublicationDao = transaction.createDao(RatingPublicationDao.class);
            List<Rating> ratings = ratingPublicationDao.getRatingsByPublicationId(publication.getId());
            for (Rating rating : ratings) {
                if (rating.getClass() == Dislike.class && rating.getUser().getId() == user.getId()) {
                    transactionFactory.close();
                    return true;
                }
            }
            transactionFactory.close();
            return false;
        } catch (PersistentException | TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean hasDislikedComment(Comment comment, User user) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            RatingCommentDao ratingCommentDao = transaction.createDao(RatingCommentDao.class);
            List<Rating> ratings = ratingCommentDao.getRatingsByCommentId(comment.getId());
            for (Rating rating : ratings) {
                if (rating.getClass() == Dislike.class && rating.getUser().getId() == user.getId()) {
                    transactionFactory.close();
                    return true;
                }
            }
            transactionFactory.close();
            return false;
        } catch (PersistentException | TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean hasSubscribed(Community community, User user) throws ServiceException {
        try {
            transactionFactory = new TransactionFactoryImpl();
            transaction = transactionFactory.createTransaction();
            CommunityDao communityDao = transaction.createDao(CommunityDao.class);
            UserDao userDao = transaction.createDao(UserDao.class);
            community = communityDao.findEntityById(community.getId());
            for (User follower : community.getFollowers()) {
                follower = userDao.findEntityById(follower.getId());
                if (follower.equals(user)) {
                    transactionFactory.close();
                    return true;
                }
            }
            transactionFactory.close();
            return false;
        } catch (PersistentException | TransactionException | DaoException e) {
            throw new ServiceException(e);
        }
    }
}
