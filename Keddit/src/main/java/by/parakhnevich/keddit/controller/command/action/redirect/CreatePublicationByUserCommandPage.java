package by.parakhnevich.keddit.controller.command.action.redirect;

import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreatePublicationByUserCommandPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().setAttribute("user", request.getSession().getAttribute("user"));
        request.setAttribute("user", request.getSession().getAttribute("user"));
        request.getRequestDispatcher(CommandPage.CREATE_PUBLICATION).forward(request, response);
    }
}
