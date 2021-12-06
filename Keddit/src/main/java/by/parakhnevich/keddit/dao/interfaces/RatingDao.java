package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.bean.publication.Rating;
import by.parakhnevich.keddit.dao.exception.DaoException;

import java.util.List;

/**
 * Interface that divides into 2 interfaces
 * @see BaseDao
 * @see RatingCommentDao
 * @see RatingPublicationDao
 */
public interface RatingDao extends BaseDao {
    List<Rating> findAll() throws DaoException;

    List<Rating> findByUserId(long id) throws DaoException;
}
