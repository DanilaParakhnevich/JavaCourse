package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.bean.publication.Rating;
import by.parakhnevich.keddit.dao.exception.DaoException;

import java.util.List;

public interface RatingDao extends BaseDao {
    List<Rating> findAll() throws DaoException;

    public boolean deleteRatingsByUserId(long id) throws DaoException;

    public List<Rating> findByUserId(long id) throws DaoException;
}
