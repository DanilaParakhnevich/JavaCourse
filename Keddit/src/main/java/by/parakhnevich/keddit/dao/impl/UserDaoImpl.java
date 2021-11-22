package by.parakhnevich.keddit.dao.impl;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.exception.DaoException;
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
    private static final String SQL_SELECT_USER_BY_MAIL =
            "SELECT id, mail, password, nickname, date, photo, role , is_banned FROM users WHERE mail=?";
    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT id, mail, password, nickname, date, photo, role , is_banned FROM users WHERE id=?";
    private static final String SQL_CREATE_USER =
            "INSERT INTO users (id, mail, password, nickname, date, photo, role, is_banned)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_USER = "UPDATE users SET id = ?, mail = ?, password = ?, nickname = ?, " +
            "date = ?, photo = ?, role = ? ,is_banned = ? WHERE id = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM users WHERE id = ?";
    private static final String SQL_SELECT_USERS_BY_FOLLOWED_COMMUNITY =
            "SELECT id, mail, password, nickname, date, users.photo, role, is_banned " +
                    "FROM users INNER JOIN followers ON followers.id_user = users.id WHERE followers.id_community = ?";
    Mapper mapper = new Mapper();
    Connection connection;

    @Override
    public List<User> findUsersByFollowedCommunity(Community community) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USERS_BY_FOLLOWED_COMMUNITY)){
            List<User> users= new ArrayList<>();
            statement.setLong(1, community.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(mapper.mapUser(resultSet));
            }
            return users;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public User findUserByMail(String mail) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_MAIL)){
            statement.setString(1, mail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapper.mapUser(resultSet);
            }
            return null;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_USERS)){
            List<User> users= new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                users.add(mapper.mapUser(resultSet));
            }
            return users;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public User findEntityById(Long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_ID)) {
            User user = null;
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = mapper.mapUser(resultSet);
                user.setNickname(resultSet.getString("nickname"));
                user.setId(id);
            }
            return user;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(User user) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            statement.setLong(1, user.getId());
            return statement.executeUpdate() == 1;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean create(User user) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER)) {
            fillUserData(user, statement);
            return statement.executeUpdate() == 1;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public User update(User user) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)) {
            statement.setLong(8, user.getId());
            fillUserData(user, statement);
            statement.executeUpdate();
            return user;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public User findUserByNickname(String nickname) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_NICKNAME)) {
            User user = null;
            statement.setString(1, nickname);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                user = mapper.mapUser(resultSet);
            }
            return user;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private void fillUserData(User user, PreparedStatement statement) throws SQLException {
        statement.setLong(1, user.getId());
        statement.setString(2, user.getMail());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getNickname());
        statement.setString(5, user.getDate().toString());
        if (user.getPhoto() == null) {
            statement.setString(6, null);
        }
        else {
            statement.setString(6, user.getPhoto().getName());
        }
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

