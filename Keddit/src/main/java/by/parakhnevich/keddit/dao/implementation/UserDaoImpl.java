package by.parakhnevich.keddit.dao.implementation;

import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.connection.ConnectionPool;
import by.parakhnevich.keddit.exception.DaoException;
import by.parakhnevich.keddit.dao.mapper.Mapper;
import by.parakhnevich.keddit.dao.interfaces.UserDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserDaoImpl implements UserDao {
    private static final String SQL_SELECT_ALL_USERS =
            "SELECT id, mail, password, nickname, date, photo, role , is_banned FROM users";
    private static final String SQL_SELECT_USER_BY_NICKNAME =
            "SELECT id, mail, password, nickname, date, photo, role , is_banned FROM users WHERE nickname=?";
    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT id, mail, password, nickname, date, photo, role , is_banned FROM users WHERE id=?";
    private static final String SQL_CREATE_USER =
            "INSERT INTO users (id, mail, password, nickname, date, photo, role, is_banned)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_USER = "UPDATE users SET id = ?, mail = ?, password = ?, nickname = ?, " +
            "date = ?, photo = ?, role = ? ,is_banned = ? WHERE id = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM users WHERE id = ?";

    Mapper mapper = new Mapper();

    @Override
    public List<User> findAll() throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_USERS)){
            List<User> users= new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(mapper.mapUser(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public User findEntityById(Long id) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_ID)) {
            User user;
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            user = mapper.mapUser(resultSet);
            user.setNickname(resultSet.getString("nickname"));
            user.setId(id);
            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(User user) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            statement.setLong(1, user.getId());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public boolean create(User user) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER)) {
            fillUserData(user, statement);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public User update(User user) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)) {
            statement.setLong(8, user.getId());
            fillUserData(user, statement);
            statement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public User findUserByNickname(String nickname) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_NICKNAME)) {
            User user;
            statement.setString(1, nickname);
            ResultSet resultSet = statement.executeQuery();
            user = mapper.mapUser(resultSet);
            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private void fillUserData(User user, PreparedStatement statement) throws SQLException {
        statement.setLong(1, user.getId());
        statement.setString(2, user.getMail());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getNickname());
        statement.setString(5, user.getDate().toString());
        statement.setString(6, user.getPhoto().getName());
        int role = switch (user.getRole().toString().toLowerCase(Locale.ROOT)) {
            case "admin" -> 3;
            case "moderator" -> 2;
            default -> 1;
        };
        statement.setString(7, String.valueOf(role));
        int value = user.isBanned() ? 1 : 0;
        statement.setInt(8, value);
    }
}

