package by.parakhnevich.keddit.controller.command.action.redirect;

import by.parakhnevich.keddit.bean.user.Role;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditRoleCommandPage implements Command {
    Logger logger = LogManager.getLogger(EditRoleCommandPage.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            User user = (User) request.getSession().getAttribute("user");
            if (user.getRole() != Role.ADMIN) {
                request.getSession().setAttribute("user", user);
                request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request, response);
                return;
            }
            request.setAttribute("id", request.getParameter("id"));
            request.setAttribute("nickname", userService.selectById(Long.parseLong(request.getParameter("id"))).getNickname());
            request.getSession().setAttribute("user", userService.selectById(user.getId()));
            request.getRequestDispatcher(CommandPage.EDIT_ROLE_PAGE).forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request, response);
        }
    }
}
