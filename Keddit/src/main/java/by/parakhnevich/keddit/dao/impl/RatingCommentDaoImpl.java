package by.parakhnevich.keddit.dao.impl;


import by.parakhnevich.keddit.bean.publication.Comment;
import by.parakhnevich.keddit.bean.publication.Like;
import by.parakhnevich.keddit.bean.publication.Rating;
import by.parakhnevich.keddit.dao.mapper.Mapper;
import by.parakhnevich.keddit.dao.interfaces.RatingCommentDao;
import by.parakhnevich.keddit.dao.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingCommentDaoImpl implements RatingCommentDao {
    private static final String SQL_SELECT_ALL_RATINGS =
            "SELECT id_user, is_like FROM like_comments";
    private static final String SQL_SELECT_RATING_BY_COMMENT_ID =
            "SELECT id_user, is_like FROM like_comments WHERE id_comment=?";
    private static final String SQL_SELECT_RATING_BY_USER_ID =
            "SELECT id_user, is_like FROM like_comments WHERE id_user=?";
    private static final String SQL_DELETE_RATING_BY_USER_ID =
            "DELETE FROM like_comments WHERE id_user=?";
    private static final String SQL_CREATE_RATING =
            "INSERT INTO like_comments (id_user, id_comment, is_like)" +
                    " VALUES (?, ?, ?)";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM like_comments WHERE (id_user=? AND id_comment=?)";
    Mapper mapper = new Mapper();
    Connection connection;

    public RatingCommentDaoImpl(Connection connection) {
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
    public List<Rating> getRatingsByCommentId(long id) throws DaoException {
        try (PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_RATING_BY_COMMENT_ID)) {
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
    public List<Rating> getRatingsByComment(Comment comment) throws DaoException {
        try (PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_RATING_BY_COMMENT_ID)) {
            List<Rating> ratings = new ArrayList<>();
            statement.setLong(1, comment.getId());
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
    public boolean addRatingByCommentId(long id, Rating rating) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_RATING)) {
            fillingRating(statement, id, rating);
            return statement.executeUpdate() == 1;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean deleteRatingByCommentId(long id, Rating rating) throws DaoException {
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
}
