package by.parakhnevich.keddit.controller.command.action.redirect;

import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.Role;
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

public class OnModerationCommandPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        try {
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = userService.selectById(((User) request.getSession().getAttribute("user")).getId());
        PublicationService publicationService = ServiceFactory.getInstance().getPublicationService();
        List<Publication> publications = publicationService.selectAll();
        publications.removeIf(a -> !a.isOnModeration());
        if (user.getRole() == Role.USER) {
            request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request, response);
            return;
        }
        request.setAttribute("publications", publications);
        request.getSession().setAttribute("user", user);
        request.getRequestDispatcher(CommandPage.PUBLICATIONS_ON_MODERATION).forward(request, response);
        } catch (TransactionException e) {
            e.printStackTrace();
        }
    }
}
