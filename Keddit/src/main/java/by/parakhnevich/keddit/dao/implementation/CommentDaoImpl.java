package by.parakhnevich.keddit.dao.implementation;

import by.parakhnevich.keddit.bean.publication.Comment;
import by.parakhnevich.keddit.connection.ConnectionPool;
import by.parakhnevich.keddit.dao.interfaces.CommentDao;
import by.parakhnevich.keddit.dao.mapper.Mapper;
import by.parakhnevich.keddit.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private static final String SQL_SELECT_ALL_COMMENTS =
            "SELECT id, id_user, id_publications, photo, content, date FROM comments";
    private static final String SQL_SELECT_COMMENT_BY_USER_ID =
            "SELECT id, id_user, id_publications, photo, content, date FROM comments WHERE id_user=?";
    private static final String SQL_SELECT_COMMENT_BY_PUBLICATION_ID =
            "SELECT id, id_user, id_publications, photo, content, date FROM comments WHERE id_publications=?";
    private static final String SQL_SELECT_COMMENT_BY_ID =
            "SELECT id, id_user, id_publications, photo, content, date FROM comments WHERE id=?";
    private static final String SQL_CREATE_COMMENT =
            "INSERT INTO comments (id, id_user, id_publications, photo, content, date)" +
                    " VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_COMMENT = "UPDATE comments SET id = ?, id_user = ?, photo = ?, " +
            "content = ?, date = ? WHERE id = ?";
    private static final String SQL_REMOVE_BY_ID = "DELETE FROM comments WHERE id = ?";
    private static final String SQL_REMOVE_BY_USER_ID = "DELETE FROM comments WHERE id_user = ?";
    private final Mapper mapper = new Mapper();

    @Override
    public List<Comment> findAll() throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_COMMENTS)) {
            List<Comment> comments = new ArrayList<>();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                comments.add(mapper.mapComment(resultSet));
            }
            return comments;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Comment findEntityById(Long id) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_COMMENT_BY_ID)) {
            Comment comment;
            statement.setLong(1, id);
            ResultSet resultSet = statement.getResultSet();
            comment = mapper.mapComment(resultSet);
            return comment;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Comment comment) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        try (Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_REMOVE_BY_ID)) {
            statement.setLong(1, comment.getId());
            return statement.executeUpdate() == 1;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_REMOVE_BY_ID)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean createCommentByPublicationId(Comment comment, long id) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_COMMENT)) {
            fillCommentData(comment, id, statement);
            return statement.executeUpdate() == 1;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean deleteByUserId(long id) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_REMOVE_BY_USER_ID)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Comment update(Comment comment) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_COMMENT)){
            statement.setLong(6, comment.getId());
            fillCommentData(comment, comment.getId(), statement);
            statement.executeUpdate();
            return comment;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public List<Comment> findCommentsByPublicationId(long id) throws DaoException{
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_COMMENT_BY_PUBLICATION_ID)) {
            List<Comment> comments = new ArrayList<>();
            statement.setLong(1, id);
            ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    comments.add(mapper.mapComment(resultSet));
                }
            return comments;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Comment> findCommentsByUserId(long id) throws DaoException{
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_COMMENT_BY_USER_ID)) {
            List<Comment> comments = new ArrayList<>();
            statement.setLong(1, id);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                comments.add(mapper.mapComment(resultSet));
            }
            return comments;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private void fillCommentData(Comment comment, long id, PreparedStatement statement) throws SQLException {
        statement.setLong(1, comment.getId());
        statement.setLong(2, comment.getUser().getId());
        statement.setLong(3, id);
        statement.setString(4, comment.getPhoto().getName());
        statement.setString(5, comment.getContent());
        statement.setString(6, comment.getDate().toString());
    }

    @Override
    public boolean create(Comment comment) throws DaoException {
        throw new UnsupportedOperationException();
    }
}