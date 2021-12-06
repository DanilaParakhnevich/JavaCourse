package by.parakhnevich.keddit.service;

import by.parakhnevich.keddit.service.impl.*;
import by.parakhnevich.keddit.service.interfaces.*;

/**
 * Factory for Service classes
 * @see Service
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final UserService userService = new UserServiceImpl();
    private final PublicationService publicationService = new PublicationServiceImpl();
    private final CommentService commentService = new CommentServiceImpl();
    private final RatingFromPublicationService ratingFromPublicationService
            = new RatingFromPublicationServiceImpl();
    private final RatingFromCommentService rating = new RatingFromCommentServiceImpl();
    private final CommunityService communityService = new CommunityServiceImpl();


    private ServiceFactory(){}

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ServiceFactory getInstance() {
        return instance;
    }

    /**
     * Gets user service.
     *
     * @return the user service
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Gets publication service.
     *
     * @return the publication service
     */
    public PublicationService getPublicationService() {
        return publicationService;
    }

    /**
     * Gets comment service.
     *
     * @return the comment service
     */
    public CommentService getCommentService() {
        return commentService;
    }

    /**
     * Gets rating from publication service.
     *
     * @return the rating from publication service
     */
    public RatingFromPublicationService getRatingFromPublicationService() {
        return ratingFromPublicationService;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public RatingFromCommentService getRating() {
        return rating;
    }

    /**
     * Gets community service.
     *
     * @return the community service
     */
    public CommunityService getCommunityService() {
        return communityService;
    }
}
