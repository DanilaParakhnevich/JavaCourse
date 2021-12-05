package by.parakhnevich.keddit.controller.command.action.edit;

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

public class SearchCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = userService.selectById(((User) request.getSession().getAttribute("user")).getId());
        switch (request.getParameter("radio")) {
            case "1" -> {
                request.setAttribute("publications", ServiceFactory.getInstance().getPublicationService()
                        .selectByTag(request.getParameter("text")));
                request.getRequestDispatcher(CommandPage.SEARCH_BY_TAG_PAGE).forward(request, response);
            }
            case "2" -> {
                request.setAttribute("communities", ServiceFactory.getInstance().getCommunityService()
                        .selectByName(request.getParameter("text")));
                request.getRequestDispatcher(CommandPage.SEARCH_BY_COMMUNITY_PAGE).forward(request, response);
            }
            case "3" -> {
                request.setAttribute("user_to_find", ServiceFactory.getInstance().getUserService().
                        selectByNickname(request.getParameter("text")));
                request.getRequestDispatcher(CommandPage.SEARCH_BY_USER_PAGE).forward(request, response);
            }
            default -> {
            }
        }
        request.getSession().setAttribute("user", user);
    }
}
