package by.parakhnevich.keddit.service.impl;

import by.parakhnevich.keddit.bean.user.Role;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.dao.exception.DaoException;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.dao.exception.TransactionException;
import by.parakhnevich.keddit.dao.impl.TransactionFactoryImpl;
import by.parakhnevich.keddit.dao.interfaces.CommunityDao;
import by.parakhnevich.keddit.dao.interfaces.PublicationDao;
import by.parakhnevich.keddit.dao.interfaces.Transaction;
import by.parakhnevich.keddit.dao.interfaces.UserDao;
import by.parakhnevich.keddit.service.PasswordService;
import by.parakhnevich.keddit.service.exception.ServiceException;
import org.testng.annotations.Test;

import java.io.File;
import java.sql.Timestamp;

import static org.testng.Assert.*;

public class UserServiceImplTest {
    private UserServiceImpl userService = new UserServiceImpl();

    @Test
    public void testSelectById() throws ServiceException {
        User user = userService.selectById(1);
        User userToCheck = new User();
        userToCheck.setRole(Role.ADMIN);
        userToCheck.setNickname("dendilll");
        userToCheck.setMail("beylon228822@gmail.com");
        userToCheck.setPassword("$2a$10$NJBA5.rLveCO6tQN5uu27OCjUGKnbUG4By9l5bXucdUfttYrDUhUS");
        userToCheck.setId(1);
        userToCheck.setBanned(true);
        userToCheck.setPhoto(new File("D:\\Projects\\JavaCourse\\Keddit\\src\\main\\webapp\\photos\\82f560b4-1943-4153-b037-395a18404c4edendilll.jpg"));
        userToCheck.setDate(Timestamp.valueOf("2021-10-10 16:54:43"));
        assertEquals(user, userToCheck);
    }
}