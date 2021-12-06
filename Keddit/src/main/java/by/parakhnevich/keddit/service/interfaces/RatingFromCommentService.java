package by.parakhnevich.keddit.service.interfaces;

import by.parakhnevich.keddit.bean.publication.Comment;
import by.parakhnevich.keddit.bean.publication.Rating;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.service.exception.ServiceException;

import java.util.List;


/**
 * @see Service
 */
public interface RatingFromCommentService extends Service<Rating> {
    /**
     * Select by user list.
     *
     * @param user the user
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Rating> selectByUser(User user) throws ServiceException;

    /**
     * Select by comment list.
     *
     * @param comment the comment
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Rating> selectByComment(Comment comment) throws ServiceException;

    /**
     * Add rating.
     *
     * @param comment the comment
     * @param rating  the rating
     * @return the rating
     * @throws ServiceException the service exception
     */
    Rating add(Comment comment, Rating rating) throws ServiceException;

    /**
     * Delete rating.
     *
     * @param comment the comment
     * @param rating  the rating
     * @return the rating
     * @throws ServiceException the service exception
     */
    Rating delete(Comment comment, Rating rating) throws ServiceException;
}
