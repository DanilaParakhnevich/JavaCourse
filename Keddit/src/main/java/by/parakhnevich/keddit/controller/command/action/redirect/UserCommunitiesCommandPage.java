package by.parakhnevich.keddit.controller.command.action.redirect;

import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserCommunitiesCommandPage implements Command {
    Logger logger = LogManager.getLogger(UserCommunitiesCommandPage.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            User user = userService.selectById(((User)request.getSession().getAttribute("user")).getId());
            User userOwner = userService.selectById(Long.parseLong(request.getParameter("id")));
            request.setAttribute("user_owner", userOwner);
            request.setAttribute("communities", userOwner.getFollowingCommunities());
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher(CommandPage.USER_COMMUNITIES).forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request, response);
        }
    }
}
