package by.parakhnevich.keddit.controller.command.action.redirect;

import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.dao.exception.TransactionException;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.PublicationService;
import by.parakhnevich.keddit.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * The class PublicationsCommandPage that is Command for
 * Controller Pattern.
 * @see Command
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public class PublicationsCommandPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, TransactionException, ServiceException {
        PublicationService publicationService = ServiceFactory.getInstance().getPublicationService();
        UserService userService = ServiceFactory.getInstance().getUserService();
        List<Publication> publications = publicationService.selectAll();
        User user = (User)request.getSession().getAttribute("user");
        request.getSession().setAttribute("user", userService.selectById(user.getId()));
        request.setAttribute("publications", publications);
        request.setAttribute("publication_service", publicationService);
        request.setAttribute("user_service", userService);
        request.getRequestDispatcher(CommandPage.PUBLICATIONS).forward(request,response);
    }
}
