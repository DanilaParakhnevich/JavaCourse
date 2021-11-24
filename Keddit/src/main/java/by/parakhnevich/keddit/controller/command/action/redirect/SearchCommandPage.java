package by.parakhnevich.keddit.controller.command.action.redirect;

import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchCommandPage implements Command {
    Logger logger = LogManager.getLogger(SearchCommandPage.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            User user = (User) request.getSession().getAttribute("user");
            user = ServiceFactory.getInstance().getUserService().selectById(user.getId());
            request.getSession().setAttribute("user", user);
            request.setAttribute("user", user);
            request.getRequestDispatcher(CommandPage.SEARCH_PAGE).forward(request,response);
        } catch (ServiceException e) {
            logger.error(e);
        }
    }
}
