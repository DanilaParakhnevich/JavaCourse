package by.parakhnevich.keddit.service.impl;

import by.parakhnevich.keddit.bean.publication.Comment;
import by.parakhnevich.keddit.bean.publication.Like;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.exception.DaoException;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.dao.exception.TransactionException;
import by.parakhnevich.keddit.dao.impl.TransactionFactoryImpl;
import by.parakhnevich.keddit.dao.interfaces.CommentDao;
import by.parakhnevich.keddit.dao.interfaces.Transaction;
import by.parakhnevich.keddit.dao.interfaces.UserDao;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.CommentService;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.testng.Assert.*;

public class CommentServiceImplTest {
    CommentService commentService = new CommentServiceImpl();
    @Test
    public void testSelectById() throws ServiceException {
        Comment comment = commentService.selectById(1);
        Comment commentToCheck = new Comment();
        commentToCheck.setUser(new User());
        commentToCheck.getUser().setId(3);
        commentToCheck.setId(1);
        commentToCheck.setContent("ору");
        commentToCheck.setDate(Timestamp.valueOf("2021-10-31 19:38:35"));
        Like like1 = new Like();
        User user = new User();
        user.setId(1);
        like1.setUser(user);
        Like like2 = new Like();
        like2.setUser(commentToCheck.getUser());
        commentToCheck.setRatings(List.of(like1, like2));
        assertEquals(comment ,commentToCheck);
    }
}