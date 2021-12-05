package by.parakhnevich.keddit.controller.command.action.edit;

import by.parakhnevich.keddit.bean.user.Role;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditRoleCommand implements Command {
    Logger logger = LogManager.getLogger(EditRoleCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            User user = (User) request.getSession().getAttribute("user");
            if (user.getRole() != Role.ADMIN) {
                request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request, response);
                return;
            }
            User userToChange = userService.selectById(Long.parseLong(request.getParameter("id")));
            if (request.getParameter("radio") == null) {
                request.setAttribute("error_message_edit_role", "CHOOSE_SOMETHING");
                request.getRequestDispatcher((String) request.getSession().getAttribute("prev_link")).forward(request, response);
                return;
            }
            switch (Integer.parseInt(request.getParameter("radio"))) {
                case 1 :
                    userToChange.setRole(Role.ADMIN);
                    break;
                case 2 :
                    userToChange.setRole(Role.MODERATOR);
                    break;
                case 3 :
                    userToChange.setRole(Role.USER);
                    break;
            }
            userService.update(userToChange);
            request.getSession().setAttribute("user", request.getSession().getAttribute("user"));
            response.sendRedirect("/keddit.by/controller?command=user_page&id=" + userToChange.getId());
        } catch (ServiceException | PersistentException e) {
            logger.error(e);
            request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request, response);
        }
    }
}
