package by.parakhnevich.keddit.service.interfaces;

import by.parakhnevich.keddit.bean.publication.Comment;
import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.service.exception.ServiceException;

import java.util.List;

public interface CommentService extends Service<Comment>{
    public List<Comment> selectByUser(User user) throws ServiceException;

    public List<Comment> selectByPublication(Publication publication) throws ServiceException;

    public int getCountOfLikes(Comment comment) throws ServiceException;

    public int getCountOfDislikes(Comment comment) throws ServiceException;

    long getFreeId() throws ServiceException;
}
