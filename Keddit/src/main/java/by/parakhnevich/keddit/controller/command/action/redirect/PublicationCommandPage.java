package by.parakhnevich.keddit.controller.command.action.redirect;

import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.impl.CommentServiceImpl;
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

public class PublicationCommandPage implements Command {
    Logger logger = LogManager.getLogger(PublicationsCommandPage.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            PublicationService publicationService = ServiceFactory.getInstance().getPublicationService();
            Publication publication = publicationService.selectById(Long.parseLong(request.getParameter("id")));
            request.setAttribute("publication", publication);
            User user = userService.selectById(((User)request.getSession().getAttribute("user")).getId());
            request.getSession().setAttribute("user", user);
            request.setAttribute("publication_service", publicationService);
            request.setAttribute("comment_service", ServiceFactory.getInstance().getCommentService());
            request.setAttribute("user_service", userService);
            request.setAttribute("user", user);
            request.getRequestDispatcher(CommandPage.PUBLICATION_PAGE).forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
        }
    }
}
