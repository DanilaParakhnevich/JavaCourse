package by.parakhnevich.keddit.controller.command.action.edit;

import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.Role;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.PublicationService;
import by.parakhnevich.keddit.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class DeletePublicationCommand that is Command for
 * Controller Pattern.
 * @see Command
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public class DeletePublicationCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException, PersistentException {
        PublicationService publicationService = ServiceFactory.getInstance().getPublicationService();
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = userService.selectById(((User)(request.getSession().getAttribute("user"))).getId());
        Publication publication = publicationService.selectById(Long.parseLong(request.getParameter("id")));
        if (user.getId() != publication.getUser().getId() && user.getRole() != Role.ADMIN) {
            request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request, response);
            return;
        }
        publicationService.delete(publication);
        if (publication.getPhoto() != null) {
            publication.getPhoto().delete();
        }
        request.getSession().setAttribute("user", user);
        response.sendRedirect("/keddit.by/controller?command=publications");
    }
}
