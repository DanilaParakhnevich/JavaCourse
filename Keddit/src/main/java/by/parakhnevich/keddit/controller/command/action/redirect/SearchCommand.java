package by.parakhnevich.keddit.controller.command.action.redirect;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.dao.exception.TransactionException;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * The class SearchCommand that is Command for
 * Controller Pattern.
 * @see Command
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public class SearchCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException, TransactionException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = userService.selectById(((User) request.getSession().getAttribute("user")).getId());
        switch (request.getParameter("radio")) {
            case "1" -> {
                request.setAttribute("publications", ServiceFactory.getInstance().getPublicationService()
                        .selectByTag(request.getParameter("text")));
                request.getRequestDispatcher(CommandPage.SEARCH_BY_TAG_PAGE).forward(request, response);
            }
            case "2" -> {
                List<Community> communities = ServiceFactory.getInstance().getCommunityService().selectAll();
                communities.removeIf(a -> !a.getName().contains(request.getParameter("text")));
                request.setAttribute("communities", communities);
                request.getRequestDispatcher(CommandPage.SEARCH_BY_COMMUNITY_PAGE).forward(request, response);
            }
            case "3" -> {
                List<User> users = ServiceFactory.getInstance().getUserService()
                        .selectAll();
                users.removeIf(a -> !a.getNickname().contains(request.getParameter("text")));
                request.setAttribute("users", users);
                request.getRequestDispatcher(CommandPage.SEARCH_BY_USER_PAGE).forward(request, response);
            }
        }
        request.getSession().setAttribute("user", user);
    }
}
