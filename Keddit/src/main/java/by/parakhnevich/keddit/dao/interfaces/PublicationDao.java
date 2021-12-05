package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.dao.exception.DaoException;

import java.util.List;

public interface PublicationDao extends BaseDao {
    List<Publication> findAll() throws DaoException;

    Publication findEntityById(Long id) throws DaoException;

    boolean delete(Publication publication) throws DaoException;

    boolean delete(Long id) throws DaoException;

    boolean create(Publication publication) throws DaoException;

    Publication update(Publication publication) throws DaoException;

    List<Publication> findPublicationsByHead(String head) throws DaoException;

    List<Publication> findPublicationsByUserId(long id) throws DaoException;

    List<Publication> findPublicationsByCommunityId(long id) throws DaoException;

    boolean createTag(long id, String tag) throws DaoException;

    boolean deleteTags(long id) throws DaoException;

    List<Long> findAllIdOfPublications() throws DaoException;

    List<Publication> findPublicationsByTag(String tag) throws DaoException;
}
