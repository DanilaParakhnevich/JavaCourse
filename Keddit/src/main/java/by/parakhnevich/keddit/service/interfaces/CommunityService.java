package by.parakhnevich.keddit.service.interfaces;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.service.exception.ServiceException;

import java.util.List;

public interface CommunityService extends Service<Community> {
    List<Community> selectByUser(User user) throws ServiceException;

    List<Community> selectByName(String name) throws ServiceException;

    void addFollower(Community community, User user) throws ServiceException;

    void deleteFollower(Community community, User user) throws ServiceException;

    int getRating(Community community) throws ServiceException;

    long getFreeId() throws ServiceException;
}
