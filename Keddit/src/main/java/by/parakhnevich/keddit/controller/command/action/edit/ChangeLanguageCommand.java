package by.parakhnevich.keddit.controller.command.action.edit;

import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        switch (request.getParameter("value")) {
            case "1" -> request.getSession().setAttribute("locale", "by");
            case "3" -> request.getSession().setAttribute("locale", "en");
            default -> request.getSession().setAttribute("locale", "ru");
        }
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = userService.selectById(((User)request.getSession().getAttribute("user")).getId());
        request.getSession().setAttribute("user", user);
        response.sendRedirect((String) request.getSession().getAttribute("prev_link"));
    }
}
