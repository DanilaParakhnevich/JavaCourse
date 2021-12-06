package by.parakhnevich.keddit.controller.command.action.redirect;

import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class SearchCommandPage that is Command for
 * Controller Pattern.
 * @see Command
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public class SearchCommandPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        User user = (User) request.getSession().getAttribute("user");
        user = ServiceFactory.getInstance().getUserService().selectById(user.getId());
        request.getSession().setAttribute("user", user);
        request.getRequestDispatcher(CommandPage.SEARCH_PAGE).forward(request,response);
    }
}
