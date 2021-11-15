package by.parakhnevich.keddit.service.interfaces;

import by.parakhnevich.keddit.bean.publication.Comment;
import by.parakhnevich.keddit.bean.publication.Rating;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.exception.ServiceException;

import java.util.List;

public interface RatingFromCommentService extends Service<Rating> {
    public List<Rating> selectByUser(User user) throws ServiceException;

    public List<Rating> selectByComment(Comment comment) throws ServiceException;

    public Rating add(Comment comment, Rating rating) throws ServiceException;

    public Rating delete(Comment comment, Rating rating) throws ServiceException;
}
