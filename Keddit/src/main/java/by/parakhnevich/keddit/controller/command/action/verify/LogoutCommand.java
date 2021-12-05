package by.parakhnevich.keddit.controller.command.action.verify;

import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        request.getSession().invalidate();
        response.sendRedirect("/keddit.by/controller?command=login_page");
    }
}
