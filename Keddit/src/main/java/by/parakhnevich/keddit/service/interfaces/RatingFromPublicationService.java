package by.parakhnevich.keddit.service.interfaces;

import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.publication.Rating;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.service.exception.ServiceException;

import java.util.List;

/**
 * @see Service
 */
public interface RatingFromPublicationService extends Service<Rating> {
    /**
     * Select by user list.
     *
     * @param user the user
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Rating> selectByUser(User user) throws ServiceException;

    /**
     * Select by publication list.
     *
     * @param publication the publication
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Rating> selectByPublication(Publication publication) throws ServiceException;

    /**
     * Add rating.
     *
     * @param publication the publication
     * @param rating      the rating
     * @return the rating
     * @throws ServiceException the service exception
     */
    Rating add(Publication publication, Rating rating) throws ServiceException;

    /**
     * Delete rating.
     *
     * @param publication the publication
     * @param rating      the rating
     * @return the rating
     * @throws ServiceException the service exception
     */
    Rating delete(Publication publication, Rating rating) throws ServiceException;
}
