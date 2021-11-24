package by.parakhnevich.keddit.controller.command.action.redirect;

import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
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

public class UserCommandPage implements Command {
    Logger logger = LogManager.getLogger(UserCommandPage.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            PublicationService publicationService = ServiceFactory.getInstance().getPublicationService();
            User userOwner = userService.selectById(Long.parseLong(request.getParameter("id")));
            User user = (User) request.getSession().getAttribute("user");
            request.setAttribute("publications", publicationService.selectByUser(user));
            request.setAttribute("user", userService.selectById(user.getId()));
            request.getSession().setAttribute("user", request.getAttribute("user"));
            request.setAttribute("user_owner", userOwner);
            request.setAttribute("user_service", userService);
            request.setAttribute("publication_service", publicationService);
            request.setAttribute("publications", publicationService.selectByUser(userOwner));
            request.getRequestDispatcher(CommandPage.USER_PAGE).forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
        }
    }
}
