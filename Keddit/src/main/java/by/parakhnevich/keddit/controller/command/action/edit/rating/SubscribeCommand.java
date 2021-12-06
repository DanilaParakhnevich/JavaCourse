package by.parakhnevich.keddit.controller.command.action.edit.rating;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.CommunityService;
import by.parakhnevich.keddit.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class SubscribeCommand that is Command for
 * Controller Pattern.
 * @see Command
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public class SubscribeCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = userService.selectById(((User) request.getSession().getAttribute("user")).getId());
        CommunityService communityService = ServiceFactory.getInstance().getCommunityService();
        Community community = communityService.selectById(Long.parseLong(request.getParameter("id")));
        if (community.getUser().getId() == user.getId()) {
            request.setAttribute("error_message_subscribe", "You cannot unfollow your community");
            request.setAttribute("community", community);
            request.setAttribute("user", user);
            request.getSession().setAttribute("user", user);
            response.sendRedirect(String.valueOf(request.getSession().getAttribute("prev_link")));
            return;
        }
        if (userService.hasSubscribed(community, user)) {
            communityService.deleteFollower(community, user);
        } else {
            communityService.addFollower(community, user);
        }
        request.setAttribute("community", community);
        request.getSession().setAttribute("user", user);
        response.sendRedirect(String.valueOf(request.getSession().getAttribute("prev_link")));
    }
}
