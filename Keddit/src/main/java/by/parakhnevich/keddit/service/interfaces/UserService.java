package by.parakhnevich.keddit.service.interfaces;

import by.parakhnevich.keddit.bean.publication.Comment;
import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.publication.Publication;
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

    boolean hasLikedPublication(Publication publication, User user) throws ServiceException;

    boolean hasLikedComment(Comment comment, User user) throws ServiceException;

    boolean hasDislikedPublication(Publication publication, User user) throws ServiceException;

    boolean hasDislikedComment(Comment comment, User user) throws ServiceException;

    boolean hasSubscribed(Community community, User user) throws ServiceException;
}
