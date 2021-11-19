package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.exception.DaoException;

import java.util.List;

public interface UserDao extends BaseDao<Long, User> {
    User findUserByNickname(String nickname) throws DaoException;

    List<User> findUsersByFollowedCommunity(Community community) throws DaoException;

    User findUserByMail(String mail) throws DaoException;
}