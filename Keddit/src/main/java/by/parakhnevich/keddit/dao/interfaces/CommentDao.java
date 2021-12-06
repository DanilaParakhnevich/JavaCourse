package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.bean.publication.Comment;
import by.parakhnevich.keddit.dao.exception.DaoException;

import java.util.List;

/**
 * @see BaseDao
 */
public interface CommentDao extends BaseDao {
    List<Comment> findAll() throws DaoException;

    Comment findEntityById(Long id) throws DaoException;

    boolean delete(Comment comment) throws DaoException;

    boolean delete(Long id) throws DaoException;

    boolean create(Comment comment) throws DaoException;

    Comment update(Comment t) throws DaoException;

    List<Comment> findCommentsByPublicationId(long id) throws DaoException;

    List<Comment> findCommentsByUserId(long id) throws DaoException;

    void createCommentByPublicationId(Comment comment, long id) throws DaoException;
}
