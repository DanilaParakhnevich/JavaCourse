package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.bean.publication.Comment;
import by.parakhnevich.keddit.exception.DaoException;

import java.util.List;

public interface CommentDao extends BaseDao<Long, Comment> {
    public List<Comment> findCommentsByPublicationId(long id) throws DaoException;

    public List<Comment> findCommentsByUserId(long id) throws DaoException;

    public boolean createCommentByPublicationId(Comment comment, long id) throws DaoException;

    public boolean deleteByUserId(long id) throws DaoException;
}
