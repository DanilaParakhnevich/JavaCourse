package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.exception.DaoException;

import java.util.List;

public interface UserDao extends BaseDao {
    List<User> findAll() throws DaoException;

    User findEntityById(Long id) throws DaoException;

    boolean delete(User user) throws DaoException;

    boolean delete(Long id) throws DaoException;

    boolean create(User user) throws DaoException;

    User update(User user) throws DaoException;

    User findUserByNickname(String nickname) throws DaoException;

    List<User> findUsersByFollowedCommunity(Community community) throws DaoException;

    User findUserByMail(String mail) throws DaoException;
}