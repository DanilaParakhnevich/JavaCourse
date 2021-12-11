package by.parakhnevich.keddit.service.impl;

import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.dao.exception.DaoException;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.dao.exception.TransactionException;
import by.parakhnevich.keddit.dao.impl.TransactionFactoryImpl;
import by.parakhnevich.keddit.dao.interfaces.CommunityDao;
import by.parakhnevich.keddit.dao.interfaces.Transaction;
import by.parakhnevich.keddit.dao.interfaces.UserDao;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.PublicationService;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.testng.Assert.*;

public class PublicationServiceImplTest {
    private PublicationService publicationService = new PublicationServiceImpl();

    @Test
    public void testSelectById() throws ServiceException, PersistentException, TransactionException, DaoException {
        Transaction transaction = new TransactionFactoryImpl().createTransaction();
        CommunityDao communityDao = transaction.createDao(CommunityDao.class);
        Publication publication = publicationService.selectById(1);
        Publication publicationToCheck = new Publication();
        UserDao userDao = transaction.createDao(UserDao.class);
        publicationToCheck.setHeading("Анекдот про улитку");
        publicationToCheck.setTextContent("Улитка заходит в бар, но бармен заявляет: \"У нас строгая политика в отношении улиток!\" — и ногой выпихивает ее на улицу. Через неделю улитка возвращается в бар и говорит бармену: \"Ну и зачем ты это сделал!?\"");
        publicationToCheck.setId(1);
        publicationToCheck.setDate(Timestamp.valueOf("2021-10-31 19:13:20"));
        publicationToCheck.setTags(List.of("Анекдот","УлиткаВБаре"));
        publicationToCheck.setCommunityOwner(communityDao.findEntityById(1L));
        publicationToCheck.setUser(userDao.findEntityById(1L));
        assertEquals(publication, publicationToCheck);
    }
}