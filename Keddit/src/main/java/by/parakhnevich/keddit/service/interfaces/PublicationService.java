package by.parakhnevich.keddit.service.interfaces;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.service.exception.ServiceException;

import java.util.List;

public interface PublicationService extends Service<Publication> {
    List<Publication> selectByUser(User user) throws ServiceException;

    List<Publication> selectByCommunity(Community community) throws ServiceException;

    int getCountOfLikes(Publication publication) throws ServiceException;

    int getCountOfDislikes(Publication publication) throws ServiceException;

    long getFreeId() throws ServiceException;

    List<Publication> selectByTag(String tag) throws ServiceException;
}
