package by.parakhnevich.keddit.dao;

import by.parakhnevich.keddit.dao.implementation.*;
import by.parakhnevich.keddit.dao.interfaces.*;


public final class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    private DaoFactory() {
    }

    private final CommentDao commentDao = new CommentDaoImpl();
    private final CommunityDao communityDao = new CommunityDaoImpl();
    private final UserDao userDao = new UserDaoImpl();
    private final RatingCommentDao ratingCommentDao = new RatingCommentDaoImpl();
    private final RatingPublicationDao ratingPublicationDao = new RatingPublicationDaoImpl();
    private final PublicationDao publicationDao = new PublicationDaoImpl();

    public static DaoFactory getInstance() {
        return instance;
    }

    public CommentDao getCommentDao() {
        return commentDao;
    }

    public CommunityDao getCommunityDao() {
        return communityDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public RatingCommentDao getRatingCommentDao() {
        return ratingCommentDao;
    }

    public RatingPublicationDao getRatingPublicationDao() {
        return ratingPublicationDao;
    }

    public PublicationDao getPublicationDao() {
        return publicationDao;
    }
}