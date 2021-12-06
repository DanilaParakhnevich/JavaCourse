package by.parakhnevich.keddit.controller.command.action.post;

import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.exception.ServiceException;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.interfaces.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand implements Command {
    Logger logger = LogManager.getLogger(LoginCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        String mail = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            request.setAttribute("user", userService.selectByMail(mail));
            if (userService.isExist(mail, password)) {

                response.sendRedirect(request.getContextPath() + "?command=authorized_publications");
            }
            else {
                request.setAttribute("error_message_login", "User not found, check your input data please!");
                request.getRequestDispatcher(CommandPage.LOGIN_PAGE).forward(request, response);
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
    }
}
