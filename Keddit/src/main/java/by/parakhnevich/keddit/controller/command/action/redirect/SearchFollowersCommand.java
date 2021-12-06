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
 * The class SearchFollowerCommand that is Command for
 * Controller Pattern.
 * @see Command
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public class SearchFollowersCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        CommunityService communityService = ServiceFactory.getInstance().getCommunityService();
        User user = (User) request.getSession().getAttribute("user");
        user = userService.selectById(user.getId());
        request.getSession().setAttribute("user", user);
        String text = request.getParameter("text");
        Community community = communityService.selectById(Long.parseLong(request.getParameter("id")));
        request.setAttribute("community", community);
        List<User> followers = userService.selectByCommunity(community);
        followers.removeIf(follower -> !follower.getNickname().contains(text));
        request.setAttribute("users", followers);
        request.getRequestDispatcher(CommandPage.FOLLOWERS).forward(request, response);
    }
}
