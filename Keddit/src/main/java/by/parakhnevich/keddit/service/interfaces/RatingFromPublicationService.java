package by.parakhnevich.keddit.service.interfaces;

import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.publication.Rating;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.service.exception.ServiceException;

import java.util.List;

public interface RatingFromPublicationService extends Service<Rating> {
    public List<Rating> selectByUser(User user) throws ServiceException;

    public List<Rating> selectByPublication(Publication publication) throws ServiceException;

    public Rating add(Publication publication, Rating rating) throws ServiceException;

    public Rating delete(Publication publication, Rating rating) throws ServiceException;
}
