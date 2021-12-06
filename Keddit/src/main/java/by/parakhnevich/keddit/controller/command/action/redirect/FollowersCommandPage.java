package by.parakhnevich.keddit.controller.command.action.redirect;

import by.parakhnevich.keddit.bean.publication.Community;
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
import java.util.List;

/**
 * The class FollowersCommandPage that is Command for
 * Controller Pattern.
 * @see Command
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public class FollowersCommandPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        CommunityService communityService = ServiceFactory.getInstance().getCommunityService();
        User user = userService.selectById(((User)request.getSession().getAttribute("user")).getId());
        Community community = communityService.selectById(Long.parseLong(request.getParameter("id")));
        List<User> users = userService.selectByCommunity(community);
        request.getSession().setAttribute("user", user);
        request.setAttribute("community", community);
        request.setAttribute("users", users);
        request.getRequestDispatcher(CommandPage.FOLLOWERS).forward(request, response);
    }
}
