package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.publication.Rating;
import by.parakhnevich.keddit.dao.exception.DaoException;

import java.util.List;

/**
 * @see RatingDao
 */
public interface RatingPublicationDao extends RatingDao{
    List<Rating> getRatingsByPublicationId(long id) throws DaoException;

    List<Rating> getRatingsByPublication(Publication publication) throws DaoException;

    void addRatingByPublicationId(long id, Rating rating) throws DaoException;

    void deleteRatingByPublicationId(long id, Rating rating) throws DaoException;

}
