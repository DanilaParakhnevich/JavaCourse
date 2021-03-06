package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.bean.publication.Rating;
import by.parakhnevich.keddit.exception.DaoException;

import java.util.List;

public interface RatingDao extends BaseDao<Long, Rating> {
    public boolean deleteRatingsByUserId(long id) throws DaoException;

    public List<Rating> findByUserId(long id) throws DaoException;
}
