package by.parakhnevich.keddit.controller.command.action.edit;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.user.Role;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.CommunityService;
import by.parakhnevich.keddit.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class DeleteCommunityCommand that is Command for
 * Controller Pattern.
 * @see Command
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public class DeleteCommunityCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException, PersistentException {
        CommunityService communityService = ServiceFactory.getInstance().getCommunityService();
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = userService.selectById(((User)(request.getSession().getAttribute("user"))).getId());
        Community community = communityService.selectById(Long.parseLong(request.getParameter("id")));
        if (user.getId() != community.getUser().getId() && user.getRole() != Role.ADMIN) {
            request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request, response);
            return;
        }
        communityService.delete(community);
        if (community.getPhoto() != null) {
            community.getPhoto().delete();
        }
        request.getSession().setAttribute("user", user);
        response.sendRedirect("/keddit.by/controller?command=publications");
    }
}
