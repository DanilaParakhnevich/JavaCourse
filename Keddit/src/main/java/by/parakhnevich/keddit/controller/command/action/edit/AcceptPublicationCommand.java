package by.parakhnevich.keddit.controller.command.action.edit;

import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.PublicationService;
import by.parakhnevich.keddit.service.interfaces.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AcceptPublicationCommand implements Command {
    Logger logger = LogManager.getLogger(AcceptPublicationCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        try {
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = userService.selectById(((User)request.getSession().getAttribute("user")).getId());
        PublicationService publicationService = ServiceFactory.getInstance().getPublicationService();
        Publication publication = publicationService.selectById(Long.parseLong(request.getParameter("id")));
        publication.setOnModeration(false);
        publicationService.update(publication);
        request.getSession().setAttribute("user", user);
        response.sendRedirect("/keddit.by/controller?command=on_moderation_page");
        } catch (PersistentException e) {
            logger.error(e);
            request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request, response);
        }
    }
}
