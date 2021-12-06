package by.parakhnevich.keddit.service.interfaces;

import by.parakhnevich.keddit.bean.publication.Comment;
import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.service.exception.ServiceException;

import java.util.List;

/**
 * @see Service
 */
public interface UserService extends Service<User> {
    /**
     * Select by community list.
     *
     * @param community the community
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> selectByCommunity(Community community) throws ServiceException;

    /**
     * Is exist boolean.
     *
     * @param mail     the mail
     * @param password the password
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isExist(String mail, String password) throws ServiceException;

    /**
     * Select by nickname user.
     *
     * @param nickname the nickname
     * @return the user
     * @throws ServiceException the service exception
     */
    User selectByNickname(String nickname) throws ServiceException;

    /**
     * Gets free id.
     *
     * @return the free id
     * @throws ServiceException the service exception
     */
    long getFreeId() throws ServiceException;

    /**
     * Gets count of likes.
     *
     * @param user the user
     * @return the count of likes
     * @throws ServiceException the service exception
     */
    int getCountOfLikes(User user) throws ServiceException;

    /**
     * Gets count of dislikes.
     *
     * @param user the user
     * @return the count of dislikes
     * @throws ServiceException the service exception
     */
    int getCountOfDislikes(User user) throws ServiceException;

    /**
     * Has liked publication boolean.
     *
     * @param publication the publication
     * @param user        the user
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean hasLikedPublication(Publication publication, User user) throws ServiceException;

    /**
     * Has liked comment boolean.
     *
     * @param comment the comment
     * @param user    the user
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean hasLikedComment(Comment comment, User user) throws ServiceException;

    /**
     * Has disliked publication boolean.
     *
     * @param publication the publication
     * @param user        the user
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean hasDislikedPublication(Publication publication, User user) throws ServiceException;

    /**
     * Has disliked comment boolean.
     *
     * @param comment the comment
     * @param user    the user
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean hasDislikedComment(Comment comment, User user) throws ServiceException;

    /**
     * Has subscribed boolean.
     *
     * @param community the community
     * @param user      the user
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean hasSubscribed(Community community, User user) throws ServiceException;

    /**
     * Select by id user.
     *
     * @param id the id
     * @return the user
     * @throws ServiceException the service exception
     */
    User selectById(long id) throws ServiceException;

    /**
     * Update user.
     *
     * @param user the user
     * @return the user
     * @throws ServiceException    the service exception
     * @throws PersistentException the persistent exception
     */
    User update(User user) throws ServiceException, PersistentException;

    /**
     * Add user.
     *
     * @param user the user
     * @return the user
     * @throws ServiceException the service exception
     */
    User add(User user) throws ServiceException;

    /**
     * Delete user.
     *
     * @param user the user
     * @return the user
     * @throws ServiceException    the service exception
     * @throws PersistentException the persistent exception
     */
    User delete(User user) throws ServiceException, PersistentException;
}
