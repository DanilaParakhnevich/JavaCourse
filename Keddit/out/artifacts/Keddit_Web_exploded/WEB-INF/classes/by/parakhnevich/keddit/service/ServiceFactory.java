package by.parakhnevich.keddit.service;

import by.parakhnevich.keddit.service.impl.*;
import by.parakhnevich.keddit.service.interfaces.*;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    UserService userService = new UserServiceImpl();
    PublicationService publicationService = new PublicationServiceImpl();
    CommentService commentService = new CommentServiceImpl();
    RatingFromPublicationService ratingFromPublicationService = new RatingFromPublicationServiceImpl();
    RatingFromCommentService rating = new RatingFromCommentServiceImpl();
    CommunityService communityService = new CommunityServiceImpl();


    private ServiceFactory(){}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public PublicationService getPublicationService() {
        return publicationService;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public RatingFromPublicationService getRatingFromPublicationService() {
        return ratingFromPublicationService;
    }

    public RatingFromCommentService getRating() {
        return rating;
    }

    public CommunityService getCommunityService() {
        return communityService;
    }
}
