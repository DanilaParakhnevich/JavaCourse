package by.parakhnevich.keddit.service.interfaces;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.service.exception.ServiceException;

import java.util.List;

/**
 * @see Service
 */
public interface PublicationService extends Service<Publication> {
    /**
     * Select by user list.
     *
     * @param user the user
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Publication> selectByUser(User user) throws ServiceException;

    /**
     * Select by community list.
     *
     * @param community the community
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Publication> selectByCommunity(Community community) throws ServiceException;

    /**
     * Gets count of likes.
     *
     * @param publication the publication
     * @return the count of likes
     * @throws ServiceException the service exception
     */
    int getCountOfLikes(Publication publication) throws ServiceException;

    /**
     * Gets count of dislikes.
     *
     * @param publication the publication
     * @return the count of dislikes
     * @throws ServiceException the service exception
     */
    int getCountOfDislikes(Publication publication) throws ServiceException;

    /**
     * Gets free id.
     *
     * @return the free id
     * @throws ServiceException the service exception
     */
    long getFreeId() throws ServiceException;

    /**
     * Select by tag list.
     *
     * @param tag the tag
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Publication> selectByTag(String tag) throws ServiceException;

    /**
     * Select by id publication.
     *
     * @param id the id
     * @return the publication
     * @throws ServiceException the service exception
     */
    Publication selectById(long id) throws ServiceException;

    /**
     * Update publication.
     *
     * @param publication the publication
     * @return the publication
     * @throws ServiceException    the service exception
     * @throws PersistentException the persistent exception
     */
    Publication update(Publication publication) throws ServiceException, PersistentException;

    /**
     * Add publication.
     *
     * @param publication the publication
     * @return the publication
     * @throws ServiceException the service exception
     */
    Publication add(Publication publication) throws ServiceException;

    /**
     * Delete publication.
     *
     * @param publication the publication
     * @return the publication
     * @throws ServiceException    the service exception
     * @throws PersistentException the persistent exception
     */
    Publication delete(Publication publication) throws ServiceException, PersistentException;
}
