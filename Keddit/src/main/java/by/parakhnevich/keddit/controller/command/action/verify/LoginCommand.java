package by.parakhnevich.keddit.controller.command.action.verify;

import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandName;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.dao.exception.TransactionException;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.interfaces.PublicationService;
import by.parakhnevich.keddit.service.interfaces.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class LoginCommand implements Command {
    Logger logger = LogManager.getLogger(LoginCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        String nickname = request.getParameter("name");
        String password = request.getParameter("password");
        String willSaveLogin = request.getParameter("will_remember");
        try {
            User user = userService.selectByNickname(nickname);
            request.getSession().setAttribute("user", user);
            request.setAttribute("user",user);
            if (userService.isExist(nickname, password)) {
                request.getRequestDispatcher("controller?command=" + CommandName.PUBLICATIONS.toString().toLowerCase(Locale.ROOT)).forward(request, response);
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
