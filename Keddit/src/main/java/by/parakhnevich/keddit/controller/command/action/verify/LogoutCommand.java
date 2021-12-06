package by.parakhnevich.keddit.controller.command.action.verify;

import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class LogoutCommand that is Command for
 * Controller Pattern.
 * @see Command
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public class LogoutCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        request.getSession().invalidate();
        response.sendRedirect("/keddit.by/controller?command=login_page");
    }
}
