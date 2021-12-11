package by.parakhnevich.keddit.service.impl;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.dao.exception.DaoException;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.dao.exception.TransactionException;
import by.parakhnevich.keddit.dao.impl.TransactionFactoryImpl;
import by.parakhnevich.keddit.dao.interfaces.Transaction;
import by.parakhnevich.keddit.dao.interfaces.UserDao;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.CommunityService;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.*;

public class CommunityServiceImplTest {
    CommunityService communityService = new CommunityServiceImpl();
    @Test
    public void testSelectById() throws ServiceException, PersistentException, TransactionException, DaoException {
        Transaction transaction = new TransactionFactoryImpl().createTransaction();
        UserDao userDao = transaction.createDao(UserDao.class);
        Community community = communityService.selectById(1);
        Community communityToCheck = new Community();
        communityToCheck.setId(1);
        communityToCheck.setUser(userDao.findEntityById(1L));
        communityToCheck.setName("Keddit_Official");
        communityToCheck.setPhoto(new File("D:\\Projects\\JavaCourse\\Keddit\\src\\main\\webapp\\photos\\efd1d8ef-daee-4160-b38a-d166ce728330Keddit_Official.png"));
        assertEquals(community, communityToCheck);
    }
}