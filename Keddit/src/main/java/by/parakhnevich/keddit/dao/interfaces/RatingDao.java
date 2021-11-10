package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.bean.publication.Rating;
import by.parakhnevich.keddit.exception.DaoException;

import java.util.List;

public interface RatingDao extends BaseDao<Long, Rating> {
    public List<Rating> findRatingByUserIdAndPublicationId() throws DaoException;

    public boolean deleteRatingByUserIdAndPublicationId() throws DaoException;
}
