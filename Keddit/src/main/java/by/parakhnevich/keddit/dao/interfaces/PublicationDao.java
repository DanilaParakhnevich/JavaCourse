package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.exception.DaoException;

import java.util.List;

public interface PublicationDao extends BaseDao<Long, Publication> {
    List<Publication> findPublicationsByHead(String head) throws DaoException;

    List<Publication> findPublicationsByUserId(long id) throws DaoException;

    boolean createTag(long id, String tag) throws DaoException;

    boolean deleteTag(long id, String tag) throws DaoException;
}
