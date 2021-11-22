package by.parakhnevich.keddit.service.interfaces;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.exception.DaoException;
import by.parakhnevich.keddit.service.exception.ServiceException;

import java.util.List;

public interface PublicationService extends Service<Publication> {
    public List<Publication> selectByUser(User user) throws ServiceException;

    public List<Publication> selectByCommunity(Community community) throws ServiceException;

    public int getCountOfLikes(Publication publication) throws ServiceException;

    public int getCountOfDislikes(Publication publication) throws ServiceException;

    long getFreeId() throws ServiceException;
}
