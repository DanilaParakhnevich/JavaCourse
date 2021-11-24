package by.parakhnevich.keddit.controller.command.action.redirect;

import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.dao.exception.TransactionException;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.impl.PublicationServiceImpl;
import by.parakhnevich.keddit.service.impl.UserServiceImpl;
import by.parakhnevich.keddit.service.interfaces.PublicationService;
import by.parakhnevich.keddit.service.interfaces.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PublicationsCommandPage implements Command {
    Logger logger = LogManager.getLogger(PublicationsCommandPage.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            PublicationService publicationService = ServiceFactory.getInstance().getPublicationService();
            UserService userService = ServiceFactory.getInstance().getUserService();
            List<Publication> publications = publicationService.selectAll();
            User user = (User)request.getSession().getAttribute("user");
            request.getSession().setAttribute("user", userService.selectById(user.getId()));
            request.setAttribute("user", user);
            request.setAttribute("publications", publications);
            request.setAttribute("publication_service", publicationService);
            request.setAttribute("user_service", userService);
            request.getRequestDispatcher(CommandPage.PUBLICATIONS).forward(request,response);
        } catch (TransactionException | ServiceException e) {
            logger.error(e);
        }
    }
}
