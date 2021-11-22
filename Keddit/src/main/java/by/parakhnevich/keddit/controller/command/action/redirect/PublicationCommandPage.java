package by.parakhnevich.keddit.controller.command.action.redirect;

import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.impl.CommentServiceImpl;
import by.parakhnevich.keddit.service.impl.PublicationServiceImpl;
import by.parakhnevich.keddit.service.impl.UserServiceImpl;
import by.parakhnevich.keddit.service.interfaces.CommentService;
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
            UserService userService = new UserServiceImpl();
            PublicationService publicationService = new PublicationServiceImpl();
            CommentService commentService = new CommentServiceImpl();
            Publication publication = publicationService.selectById(Long.parseLong(request.getParameter("id")));
            publication.setComments(commentService.selectByPublication(publication));
            request.setAttribute("publication", publication);
            User user = userService.selectById(((User)request.getSession().getAttribute("user")).getId());
            request.getSession().setAttribute("user", user);
            request.setAttribute("user", user);
            request.getRequestDispatcher(CommandPage.PUBLICATION).forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
        }
    }
}
