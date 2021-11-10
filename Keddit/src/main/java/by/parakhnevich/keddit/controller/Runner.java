package by.parakhnevich.keddit.controller;


import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.implementation.RatingCommentDaoImpl;
import by.parakhnevich.keddit.dao.implementation.UserDaoImpl;
import by.parakhnevich.keddit.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Runner {
    static Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        try {
            List<User> list = new UserDaoImpl().findAll();
            RatingCommentDaoImpl ratingCommentDao = new RatingCommentDaoImpl();
            while (ratingCommentDao.deleteRatingByUserId(1L)) {
                System.out.println("yaya");
            }
            new UserDaoImpl().delete(1L);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
