package by.parakhnevich.keddit.dao.interfaces;

import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.interfaces.BaseDao;
import by.parakhnevich.keddit.exception.DaoException;

public interface UserDao extends BaseDao<Long, User> {
    User findUserByNickname(String nickname) throws DaoException;
}