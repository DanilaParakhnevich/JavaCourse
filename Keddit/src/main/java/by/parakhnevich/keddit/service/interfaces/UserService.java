package by.parakhnevich.keddit.service.interfaces;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.exception.ServiceException;

import java.util.List;

public interface UserService extends Service<User> {
    List<User> selectByCommunity(Community community) throws ServiceException;
}
