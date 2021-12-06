package by.parakhnevich.keddit.service.interfaces;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.service.exception.ServiceException;

import java.util.List;

/**
 * @see Service
 */
public interface CommunityService extends Service<Community> {
    /**
     * Select by user list.
     *
     * @param user the user
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Community> selectByUser(User user) throws ServiceException;

    /**
     * Select by name list.
     *
     * @param name the name
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Community> selectByName(String name) throws ServiceException;

    /**
     * Add follower.
     *
     * @param community the community
     * @param user      the user
     * @throws ServiceException the service exception
     */
    void addFollower(Community community, User user) throws ServiceException;

    /**
     * Delete follower.
     *
     * @param community the community
     * @param user      the user
     * @throws ServiceException the service exception
     */
    void deleteFollower(Community community, User user) throws ServiceException;

    /**
     * Gets rating.
     *
     * @param community the community
     * @return the rating
     * @throws ServiceException the service exception
     */
    int getRating(Community community) throws ServiceException;

    /**
     * Gets free id.
     *
     * @return the free id
     * @throws ServiceException the service exception
     */
    long getFreeId() throws ServiceException;

    /**
     * Select by id community.
     *
     * @param id the id
     * @return the community
     * @throws ServiceException the service exception
     */
    Community selectById(long id) throws ServiceException;

    /**
     * Update community.
     *
     * @param community the community
     * @return the community
     * @throws ServiceException    the service exception
     * @throws PersistentException the persistent exception
     */
    Community update(Community community) throws ServiceException, PersistentException;

    /**
     * Add community.
     *
     * @param community the community
     * @return the community
     * @throws ServiceException the service exception
     */
    Community add(Community community) throws ServiceException;

    /**
     * Delete community.
     *
     * @param community the community
     * @return the community
     * @throws ServiceException    the service exception
     * @throws PersistentException the persistent exception
     */
    Community delete(Community community) throws ServiceException, PersistentException;
}
