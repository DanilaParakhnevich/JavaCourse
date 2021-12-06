package by.parakhnevich.keddit.controller.command.action.verify;

import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandName;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * The class LoginCommand that is Command for
 * Controller Pattern.
 * @see Command
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public class LoginCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        String nickname = request.getParameter("name");
        String password = request.getParameter("password");
        User user = userService.selectByNickname(nickname);
        request.getSession().setAttribute("user", user);
        if (userService.isExist(nickname, password)) {
            request.getRequestDispatcher("controller?command=" + CommandName.PUBLICATIONS.toString().toLowerCase(Locale.ROOT)).forward(request, response);
        }
        else {
            request.setAttribute("error_message_login", "LOGIN_ERROR_SECOND");
            request.getRequestDispatcher(CommandPage.LOGIN_PAGE).forward(request, response);
        }
    }
}
