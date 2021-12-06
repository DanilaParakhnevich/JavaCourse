package by.parakhnevich.keddit.controller.command.action.redirect;

import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class SearchUserCommunityCommand that is Command for
 * Controller Pattern.
 * @see Command
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public class SearchUserCommunityCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = userService.selectById(((User)request.getSession().getAttribute("user")).getId());
        User userOwner = userService.selectById(Long.parseLong(request.getParameter("id")));
        userOwner.getFollowingCommunities()
                .removeIf(a -> !a.getName().contains(request.getParameter("text")));
        request.setAttribute("user_owner", userOwner);
        request.setAttribute("communities", userOwner.getFollowingCommunities());
        request.getSession().setAttribute("user", user);
        request.getRequestDispatcher(CommandPage.USER_COMMUNITIES).forward(request, response);
    }
}
