package by.parakhnevich.keddit.dao.impl;

import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.dao.exception.DaoException;
import by.parakhnevich.keddit.dao.mapper.Mapper;
import by.parakhnevich.keddit.dao.interfaces.PublicationDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublicationDaoImpl implements PublicationDao {
    private static final String SQL_SELECT_ALL_PUBLICATIONS =
            "SELECT id, id_user, head, body, photos, date, id_community, is_on_moderation, GROUP_CONCAT(tags.tag) as tag " +
                    "FROM publications INNER JOIN tags ON publications.id = tags.id_publications GROUP BY tags.id_publications";
    private static final String SQL_SELECT_PUBLICATION_BY_HEAD =
            "SELECT id, id_user, head, body, photos, date, id_community, is_on_moderation, GROUP_CONCAT(tags.tag) as tag " +
                    "FROM publications INNER JOIN tags ON publications.id = tags.id_publications WHERE head=?" +
                    "GROUP BY tags.id_publications";
    private static final String SQL_SELECT_PUBLICATION_BY_COMMUNITY_ID =
            "SELECT id, id_user, head, body, photos, date, id_community, is_on_moderation, GROUP_CONCAT(tags.tag) as tag FROM publications " +
                    "INNER JOIN tags ON publications.id = tags.id_publications WHERE id_community=? GROUP BY tags.id_publications";
    private static final String SQL_SELECT_PUBLICATION_BY_USER_ID =
            "SELECT id, id_user, head, body, photos, date, id_community, is_on_moderation, GROUP_CONCAT(tags.tag) as tag " +
                    "FROM publications INNER JOIN tags ON publications.id = tags.id_publications WHERE id_user=? " +
                    "GROUP BY tags.id_publications";
    private static final String SQL_SELECT_PUBLICATION_BY_TAG =
            "SELECT id, id_user, head, body, photos, date, id_community, is_on_moderation, GROUP_CONCAT(tags.tag) as tag " +
                    "FROM publications INNER JOIN tags ON publications.id = tags.id_publications WHERE tags.tag=?" +
                    "GROUP BY tags.id_publications";
    private static final String SQL_SELECT_ALL_ID_PUBLICATION =  "SELECT id " +
            "FROM publications";
    private static final String SQL_SELECT_PUBLICATION_BY_ID =
            "SELECT id, id_user, head, body, photos, date, id_community, is_on_moderation, GROUP_CONCAT(tags.tag) as tag " +
                    "FROM publications INNER JOIN tags ON publications.id = tags.id_publications WHERE id=? " +
                    "GROUP BY tags.id_publications";
    private static final String SQL_CREATE_PUBLICATION =
            "INSERT INTO publications (id, id_user, head, body, photos, date, id_community, is_on_moderation)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_PUBLICATION = "UPDATE publications SET id = ?, id_user = ?, head = ?, body = ?, " +
            "photos = ?, date = ?, id_community = ? ,is_on_moderation = ? WHERE id = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM publications WHERE id = ?";
    private static final String SQL_DELETE_TAGS_BY_ID = "DELETE FROM tags WHERE (id_publications = ?)";
    private static final String SQL_CREATE_TAG = "INSERT INTO tags (id_publications, tag) VALUES (?, ?)";
    Mapper mapper = new Mapper();
    Connection connection;

    public PublicationDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Publication> findPublicationsByCommunityId(long id) throws DaoException {
        try (PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_PUBLICATION_BY_COMMUNITY_ID)) {
            List<Publication> publications = new ArrayList<>();
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                publications.add(mapper.mapPublication(resultSet));
            }
            return publications;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public List<Publication> findPublicationsByUserId(long id) throws DaoException {
        try (PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_PUBLICATION_BY_USER_ID)) {
            List<Publication> publications = new ArrayList<>();
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                publications.add(mapper.mapPublication(resultSet));
            }
            return publications;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Publication> findPublicationsByHead(String head) throws DaoException {
        try (PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_PUBLICATION_BY_HEAD)) {
            List<Publication> publications = new ArrayList<>();
            statement.setString(1, head);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                publications.add(mapper.mapPublication(resultSet));
            }
            return publications;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean createTag(long id, String tag) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_TAG)) {
            statement.setLong(1, id);
            statement.setString(2, tag);
            return statement.executeUpdate() == 1;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean deleteTags(long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_TAGS_BY_ID)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Long> findAllIdOfPublications() throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_ID_PUBLICATION)) {
            List<Long> ids = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ids.add(resultSet.getLong("id"));
            }
            return ids;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Publication> findPublicationsByTag(String tag) throws DaoException {
        try (PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_PUBLICATION_BY_TAG)) {
            List<Publication> publications = new ArrayList<>();
            statement.setString(1, tag);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                publications.add(mapper.mapPublication(resultSet));
            }
            return publications;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Publication> findAll() throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_PUBLICATIONS)) {
            List<Publication> publications = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                publications.add(mapper.mapPublication(resultSet));
            }
            return publications;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Publication findEntityById(Long id) throws DaoException {
        try (PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_PUBLICATION_BY_ID)) {
            Publication publication = null;
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                publication = mapper.mapPublication(resultSet);
            }
            return publication;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Publication publication) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            statement.setLong(1, publication.getId());
            if (publication.getPhoto() != null) {
                publication.getPhoto().delete();
            }
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
    public boolean create(Publication publication) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_PUBLICATION)) {
            fillPublicationData(publication, statement);
            return statement.executeUpdate() == 1;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Publication update(Publication publication) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PUBLICATION)) {
            statement.setLong(9, publication.getId());
            fillPublicationData(publication, statement);
            statement.executeUpdate();
            return publication;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private void fillPublicationData(Publication publication, PreparedStatement statement) throws SQLException {
        statement.setLong(1, publication.getId());
        statement.setLong(2, publication.getUser().getId());
        statement.setString(3, publication.getHeading());
        statement.setString(4, publication.getTextContent());
        if (publication.getPhoto() == null) {
            statement.setString(5, null);
        }
        else {
            statement.setString(5, publication.getPhoto().getName());
        }
        statement.setString(6, publication.getDate().toString());
        if (publication.getCommunityOwner() != null) {
            statement.setLong(7, publication.getCommunityOwner().getId());
        }
        else {
            statement.setLong(7, 0);
        }
        int value = publication.isOnModeration() ? 1 : 0;
        statement.setInt(8, value);
    }
}
