package by.parakhnevich.keddit.controller;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.exception.ServiceException;
import by.parakhnevich.keddit.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Runner {
    static Logger logger = LogManager.getLogger();
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        try {
            User user = userService.selectById(2);
            System.out.println(user.getPublication(0).toString());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
