package by.parakhnevich.keddit.service.interfaces;

import by.parakhnevich.keddit.bean.publication.Comment;
import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.publication.Rating;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.exception.ServiceException;

import java.util.List;

public interface CommentService extends Service<Comment>{
    public List<Comment> selectByUser(User user) throws ServiceException;

    public List<Comment> selectByPublication(Publication publication) throws ServiceException;

    public Rating addRating(Rating rating) throws ServiceException;

    public Rating deleteRating(Rating rating) throws ServiceException;
}
