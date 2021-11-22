package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.bean.publication.Comment;
import by.parakhnevich.keddit.bean.publication.Rating;
import by.parakhnevich.keddit.dao.exception.DaoException;

import java.util.List;

public interface RatingCommentDao extends RatingDao{
    public List<Rating> getRatingsByCommentId(long id) throws DaoException;

    public List<Rating> getRatingsByComment(Comment comment) throws DaoException;

    public boolean addRatingByCommentId(long id, Rating rating) throws DaoException;

    public boolean deleteRatingByCommentId(long id, Rating rating) throws DaoException;
}
