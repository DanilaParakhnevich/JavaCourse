package by.parakhnevich.keddit.controller.command.action.redirect;

import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.PublicationService;
import by.parakhnevich.keddit.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class PublicationCommandPage that is Command for
 * Controller Pattern.
 * @see Command
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public class PublicationCommandPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        PublicationService publicationService = ServiceFactory.getInstance().getPublicationService();
        Publication publication = publicationService.selectById(Long.parseLong(request.getParameter("id")));
        request.setAttribute("publication", publication);
        User user = userService.selectById(((User)request.getSession().getAttribute("user")).getId());
        request.getSession().setAttribute("user", user);
        request.setAttribute("publication_service", publicationService);
        request.setAttribute("comment_service", ServiceFactory.getInstance().getCommentService());
        request.setAttribute("user_service", userService);
        request.getRequestDispatcher(CommandPage.PUBLICATION_PAGE).forward(request, response);
    }
}
