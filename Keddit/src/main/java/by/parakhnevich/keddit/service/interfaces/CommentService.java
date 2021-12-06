package by.parakhnevich.keddit.service.interfaces;

import by.parakhnevich.keddit.bean.publication.Comment;
import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.service.exception.ServiceException;

import java.util.List;

/**
 * @see Service
 */
public interface CommentService extends Service<Comment>{
    /**
     * Select by user list.
     *
     * @param user the user
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Comment> selectByUser(User user) throws ServiceException;

    /**
     * Select by publication list.
     *
     * @param publication the publication
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Comment> selectByPublication(Publication publication) throws ServiceException;

    /**
     * Gets count of likes.
     *
     * @param comment the comment
     * @return the count of likes
     * @throws ServiceException the service exception
     */
    int getCountOfLikes(Comment comment) throws ServiceException;

    /**
     * Gets count of dislikes.
     *
     * @param comment the comment
     * @return the count of dislikes
     * @throws ServiceException the service exception
     */
    int getCountOfDislikes(Comment comment) throws ServiceException;

    /**
     * Add comment.
     *
     * @param comment     the comment
     * @param publication the publication
     * @return the comment
     * @throws ServiceException the service exception
     */
    Comment add(Comment comment, Publication publication) throws ServiceException;

    /**
     * Gets free id.
     *
     * @return the free id
     * @throws ServiceException the service exception
     */
    long getFreeId() throws ServiceException;

    /**
     * Select by id comment.
     *
     * @param id the id
     * @return the comment
     * @throws ServiceException the service exception
     */
    Comment selectById(long id) throws ServiceException;

    /**
     * Update comment.
     *
     * @param comment the comment
     * @return the comment
     * @throws ServiceException    the service exception
     * @throws PersistentException the persistent exception
     */
    Comment update(Comment comment) throws ServiceException, PersistentException;

    /**
     * Delete comment.
     *
     * @param comment the comment
     * @return the comment
     * @throws ServiceException    the service exception
     * @throws PersistentException the persistent exception
     */
    Comment delete(Comment comment) throws ServiceException, PersistentException;
}
