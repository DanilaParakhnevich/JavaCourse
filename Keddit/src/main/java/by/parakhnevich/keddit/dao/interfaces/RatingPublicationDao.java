package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.publication.Rating;
import by.parakhnevich.keddit.dao.exception.DaoException;

import java.util.List;

public interface RatingPublicationDao extends RatingDao{
    public List<Rating> getRatingsByPublicationId(long id) throws DaoException;

    public List<Rating> getRatingsByPublication(Publication publication) throws DaoException;

    public boolean addRatingByPublicationId(long id, Rating rating) throws DaoException;

    public boolean deleteRatingByPublicationId(long id, Rating rating) throws DaoException;

}
