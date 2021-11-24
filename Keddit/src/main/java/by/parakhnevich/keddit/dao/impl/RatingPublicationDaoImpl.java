package by.parakhnevich.keddit.dao.impl;

import by.parakhnevich.keddit.bean.publication.Like;
import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.publication.Rating;
import by.parakhnevich.keddit.dao.mapper.Mapper;
import by.parakhnevich.keddit.dao.interfaces.RatingPublicationDao;
import by.parakhnevich.keddit.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RatingPublicationDaoImpl implements RatingPublicationDao {
    private static final String SQL_SELECT_ALL_RATINGS =
            "SELECT id_user, is_like FROM like_publications";
    private static final String SQL_SELECT_RATING_BY_PUBLICATION_ID =
            "SELECT id_user, is_like FROM like_publications WHERE id_publication=?";
    private static final String SQL_SELECT_RATING_BY_USER_ID =
            "SELECT id_user, is_like FROM like_publications WHERE id_user=?";
    private static final String SQL_DELETE_RATING_BY_USER_ID =
            "DELETE FROM like_publications WHERE id_user=?";
    private static final String SQL_CREATE_RATING =
            "INSERT INTO like_publications  (id_user, id_publication, is_like)" +
                    " VALUES (?, ?, ?)";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM like_publications " +
            " WHERE (id_user=? AND id_publication=?)";
    Mapper mapper = new Mapper();
    Connection connection;

    public RatingPublicationDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Rating> findAll() throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_RATINGS)) {
            List<Rating> ratings = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                ratings.add(mapper.mapRating(resultSet));
            }
            return ratings;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Rating> getRatingsByPublicationId(long id) throws DaoException {
        try (PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_RATING_BY_PUBLICATION_ID)) {
            List<Rating> ratings = new ArrayList<>();
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                ratings.add(mapper.mapRating(resultSet));
            }
            return ratings;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Rating> getRatingsByPublication(Publication publication) throws DaoException {
        try (PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_RATING_BY_PUBLICATION_ID)) {
            List<Rating> ratings = new ArrayList<>();
            statement.setLong(1, publication.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                ratings.add(mapper.mapRating(resultSet));
            }
            return ratings;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean addRatingByPublicationId(long id, Rating rating) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_RATING)) {
            fillingRating(statement, id, rating);
            return statement.executeUpdate() == 1;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean deleteRatingByPublicationId(long id, Rating rating) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            statement.setLong(1, rating.getUser().getId());
            statement.setLong(2, id);
            return statement.executeUpdate() == 1;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean deleteRatingsByUserId(long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_RATING_BY_USER_ID)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Rating> findByUserId(long id) throws DaoException {
        try (PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_RATING_BY_USER_ID)) {
            List<Rating> ratings = new ArrayList<>();
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                ratings.add(mapper.mapRating(resultSet));
            }
            return ratings;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private void fillingRating(PreparedStatement statement, long id, Rating rating) throws SQLException{
        statement.setLong(1, rating.getUser().getId());
        statement.setLong(2, id);
        int value = rating instanceof Like ? 1 : 0;
        statement.setInt(3, value);
    }

    @Override
    public Rating findEntityById(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Rating rating) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Rating rating) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Rating update(Rating rating) throws DaoException {
        throw new UnsupportedOperationException();
    }
}
