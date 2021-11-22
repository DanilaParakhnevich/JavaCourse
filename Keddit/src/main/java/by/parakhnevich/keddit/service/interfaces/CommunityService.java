package by.parakhnevich.keddit.service.interfaces;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.service.exception.ServiceException;

public interface CommunityService extends Service<Community> {
    public User addFollower(Community community, User user) throws ServiceException;

    public User deleteFollower(Community community, User user) throws ServiceException;

    public int getRating(Community community) throws ServiceException;

    long getFreeId() throws ServiceException;
}
