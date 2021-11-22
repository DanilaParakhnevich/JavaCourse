package by.parakhnevich.keddit.service.interfaces;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.service.exception.ServiceException;

import java.util.List;

public interface UserService extends Service<User> {
    List<User> selectByCommunity(Community community) throws ServiceException;

    boolean isExist(String mail, String password) throws ServiceException;

    User selectByMail(String mail) throws ServiceException;

    User selectByNickname(String nickname) throws ServiceException;

    long getFreeId() throws ServiceException;

    int getCountOfLikes(User user) throws ServiceException;

    int getCountOfDislikes(User user) throws ServiceException;
}
