package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.bean.publication.Comment;
import by.parakhnevich.keddit.bean.publication.Rating;
import by.parakhnevich.keddit.exception.DaoException;

import java.util.List;

public interface RatingCommentDao extends BaseDao<Long, Rating> {
    public List<Rating> getRatingsByCommentId(long id) throws DaoException;

    public List<Rating> getRatingsByComment(Comment comment) throws DaoException;

    public boolean addRatingByCommentId(long id, Rating rating) throws DaoException;

    public boolean deleteRatingByCommentId(long id, Rating rating) throws DaoException;

    public boolean deleteRatingByUserId(long id) throws DaoException;
}
