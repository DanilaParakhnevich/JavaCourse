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

public class SearchByTagCommandPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = userService.selectById(((User) request.getSession().getAttribute("user")).getId());
        request.setAttribute("publications", ServiceFactory.getInstance().getPublicationService()
                .selectByTag(request.getParameter("tag")));
        request.getSession().setAttribute("user", user);
        request.getRequestDispatcher(CommandPage.SEARCH_BY_TAG_PAGE).forward(request, response);
    }
}
