package by.parakhnevich.keddit.dao.impl;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.dao.interfaces.CommunityDao;
import by.parakhnevich.keddit.dao.mapper.Mapper;
import by.parakhnevich.keddit.dao.exception.DaoException;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommunityDaoImpl implements CommunityDao {
    private static final String SQL_SELECT_ALL_COMMUNITIES =
            "SELECT id, communities.id_user, name, photo, GROUP_CONCAT(followers.id_user) as user FROM communities " +
                    "LEFT JOIN followers ON communities.id = followers.id_community " +
                    "GROUP BY communities.id";
    private static final String SQL_SELECT_COMMUNITIES_BY_ID =
            "SELECT id, communities.id_user, name, photo, GROUP_CONCAT(followers.id_user) as user FROM communities " +
                    "LEFT JOIN followers ON communities.id = followers.id_community WHERE communities.id=? " +
                    "GROUP BY communities.id";
    private static final String SQL_SELECT_COMMUNITIES_BY_NAME =
            "SELECT id, communities.id_user, name, photo, GROUP_CONCAT(followers.id_user) as user FROM communities " +
                    "LEFT JOIN followers ON communities.id = followers.id_community WHERE communities.name=? " +
                    "GROUP BY communities.id";
    private static final String SQL_SELECT_COMMUNITY_BY_USER_ID =
            "SELECT id, communities.id_user, name, photo, GROUP_CONCAT(followers.id_user) as user FROM communities " +
                    "INNER JOIN followers ON communities.id = followers.id_community WHERE communities.id_user=? " +
                    "GROUP BY communities.id";
    private static final String SQL_SELECT_FOLLOWING_COMMUNITIES_BY_USER_ID =
            "SELECT id, name, photo FROM communities " +
                    "INNER JOIN followers ON communities.id = followers.id_community WHERE followers.id_user=?";
    private static final String SQL_CREATE_COMMUNITY =
            "INSERT INTO communities (id, id_user, name, photo)" +
                    " VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_COMMUNITY = "UPDATE communities SET id = ?, id_user = ?, name = ?, photo = ?" +
            " WHERE id = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM communities WHERE id = ?";
    private static final String SQL_CREATE_FOLLOWER =
            "INSERT INTO followers (id_user, id_community)" +
                    " VALUES (?, ?)";
    private static final String SQL_DELETE_FOLLOWER = "DELETE FROM followers WHERE id_user = ? AND id_community=?";

    private static final String SQL_DELETE_BY_USER_ID = "DELETE FROM communities WHERE id_user = ?";
    private static final String PATH_TO_PHOTOS = ".src/main/webapp/photos/";
    Mapper mapper = new Mapper();
    Connection connection;

    public CommunityDaoImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<Community> findAll() throws DaoException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SQL_SELECT_ALL_COMMUNITIES)) {
            List<Community> communities = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                communities.add(mapper.mapCommunity(resultSet));
            }
            return communities;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Community findEntityById(Long id) throws DaoException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SQL_SELECT_COMMUNITIES_BY_ID)) {
            Community community = null;
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                community = mapper.mapCommunity(resultSet);
            }
            return community;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Community> getCommunitiesByUserId(long id) throws DaoException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SQL_SELECT_COMMUNITY_BY_USER_ID)) {
            List<Community> communities = new ArrayList<>();
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                communities.add(mapper.mapCommunity(resultSet));
            }
            return communities;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Community> getFollowingCommunitiesByUserId(long id) throws DaoException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SQL_SELECT_FOLLOWING_COMMUNITIES_BY_USER_ID)){
            List<Community> communities = new ArrayList<>();
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                Community community = new Community();
                community.setId(resultSet.getLong("id"));
                String photo = resultSet.getString("photo");
                if (photo != null) {
                    community.setPhoto(new File(PATH_TO_PHOTOS +
                           photo ));
                }
                community.setName(resultSet.getString("name"));
                communities.add(community);
            }
            return communities;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void addFollower(long communityId, long followerId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_FOLLOWER)) {
            preparedStatement.setLong(1, followerId);
            preparedStatement.setLong(2, communityId);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteFollower(long communityId, long followerId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_FOLLOWER)) {
            preparedStatement.setLong(1, followerId);
            preparedStatement.setLong(2, communityId);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean deleteByUserId(long id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_USER_ID)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Community> getCommunitiesByName(String name) throws DaoException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SQL_SELECT_COMMUNITIES_BY_NAME)){
            List<Community> communities = new ArrayList<>();
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                Community community = new Community();
                community.setId(resultSet.getLong("id"));
                String photo = resultSet.getString("photo");
                if (photo != null) {
                    community.setPhoto(new File(PATH_TO_PHOTOS +
                            photo ));
                }
                community.setName(resultSet.getString("name"));
                communities.add(community);
            }
            return communities;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Community community) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            preparedStatement.setLong(1, community.getId());
            return preparedStatement.executeUpdate() == 1;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean create(Community community) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_COMMUNITY)) {
            fillingCommunityData(community, statement);
            return statement.executeUpdate() == 1;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Community update(Community community) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_COMMUNITY)) {
            statement.setLong(1, community.getId());
            fillingCommunityData(community, statement);
            statement.setLong(5, community.getId());
            statement.executeUpdate();
            return community;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private void fillingCommunityData(Community community, PreparedStatement statement) throws SQLException {
        statement.setLong(1, community.getId());
        statement.setLong(2, community.getUser().getId());
        statement.setString(3, community.getName());
        if (community.getPhoto() != null) {
            statement.setString(4, community.getPhoto().getName());
        }
        else {
            statement.setString(4, null);
        }
    }
}
