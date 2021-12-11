package by.parakhnevich.keddit.service.impl;

import by.parakhnevich.keddit.bean.publication.Like;
import by.parakhnevich.keddit.bean.publication.Rating;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.RatingFromCommentService;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class RatingFromCommentServiceImplTest {
    RatingFromCommentService ratingFromCommentService = new RatingFromCommentServiceImpl();
    @Test
    public void testSelectByUser() throws ServiceException {
        User user = new User();
        user.setId(1);
        List<Rating> ratings = ratingFromCommentService.selectByUser(user);
        List<Rating> ratingsToCheck = new ArrayList<>();
        Rating rating = new Like();
        rating.setUser(user);
        ratingsToCheck.add(rating);
        assertEquals(ratings, ratingsToCheck);
    }
}