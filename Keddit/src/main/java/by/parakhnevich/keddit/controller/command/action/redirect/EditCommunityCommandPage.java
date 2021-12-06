package by.parakhnevich.keddit.controller.command.action.redirect;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.user.Role;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.CommunityService;
import by.parakhnevich.keddit.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class EditCommunityCommandPage that is Command for
 * Controller Pattern.
 * @see Command
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public class EditCommunityCommandPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        CommunityService communityService = ServiceFactory.getInstance().getCommunityService();
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = userService.selectById(((User)(request.getSession().getAttribute("user"))).getId());
        Community community = communityService.selectById(Long.parseLong(request.getParameter("id")));
        if (user.getId() != community.getUser().getId() && user.getRole() != Role.ADMIN) {
            request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request, response);
            return;
        }
        request.setAttribute("community", community);
        request.getSession().setAttribute("user", user);
        request.getRequestDispatcher(CommandPage.EDIT_COMMUNITY).forward(request, response);
    }
}

