package by.parakhnevich.keddit.controller.command.action.redirect;

import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.impl.UserServiceImpl;
import by.parakhnevich.keddit.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AboutCommandPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            UserService userService = new UserServiceImpl();
            User user = (User) request.getSession().getAttribute("user");
            user = userService.selectById(user.getId());
            request.getSession().setAttribute("user", user);
            request.setAttribute("user", user);
            request.getRequestDispatcher(CommandPage.ABOUT_PAGE).forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
