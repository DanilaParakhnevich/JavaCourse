package by.parakhnevich.keddit.controller;

import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandName;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.controller.command.CommandProvider;
import by.parakhnevich.keddit.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/controller")
@MultipartConfig(location = "D:\\Projects\\JavaCourse\\Keddit\\src\\main\\webapp\\photos\\")
public class KedditController extends HttpServlet {
    static List<CommandName> commandNames = Arrays.asList(CommandName.LOGIN, CommandName.LOGIN_PAGE,
            CommandName.REGISTRATION_PAGE, CommandName.SIGN_UP);
    static Logger logger = LogManager.getLogger(KedditController.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request, response);
        }
    }

    private static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        request.getSession().setMaxInactiveInterval(-1);
        String commandName = request.getParameter("command");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null && !commandNames.contains(CommandName.valueOf(request.getParameter("command").toUpperCase()))) {
            request.setAttribute("error_message_login", "LOGIN_ERROR_FIRST");
            request.getRequestDispatcher(CommandPage.LOGIN_PAGE).forward(request, response);
        }
        else if (user != null && user.isBanned() && !commandNames.contains(CommandName.valueOf(request.getParameter("command").toUpperCase()))) {
            request.getSession().invalidate();
            request.getRequestDispatcher(CommandPage.BLOCKED_USER).forward(request, response);
        }
        else {
            if (commandName != null) {
                logger.debug(commandName);
            }
            Command command = CommandProvider.getInstance().getCommand(commandName);
            command.execute(request, response);
        }
    }
}
