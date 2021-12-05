package by.parakhnevich.keddit.controller.command.action.redirect;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.CommunityService;
import by.parakhnevich.keddit.service.interfaces.PublicationService;
import by.parakhnevich.keddit.service.interfaces.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommunityCommandPage implements Command {
    Logger logger = LogManager.getLogger(CommunityCommandPage.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            CommunityService communityService = ServiceFactory.getInstance().getCommunityService();
            UserService userService = ServiceFactory.getInstance().getUserService();
            PublicationService publicationService = ServiceFactory.getInstance().getPublicationService();
            User user = (User) request.getSession().getAttribute("user");
            user = userService.selectById(user.getId());
            request.getSession().setAttribute("user", user);
            request.setAttribute("community", communityService.selectById(Long.parseLong(request.getParameter("id"))));
            request.setAttribute("community_service", communityService);
            request.setAttribute("publication_service", publicationService);
            request.setAttribute("publications", publicationService.selectByCommunity((Community) request.getAttribute("community")));
            request.setAttribute("user_service", userService);
            request.getRequestDispatcher(CommandPage.COMMUNITY_PAGE).forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
        }
    }
}
