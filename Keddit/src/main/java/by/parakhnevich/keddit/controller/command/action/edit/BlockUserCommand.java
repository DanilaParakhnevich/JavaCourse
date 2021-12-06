package by.parakhnevich.keddit.controller.command.action.edit;

import by.parakhnevich.keddit.bean.user.Role;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class BlockUserCommand that is Command for
 * Controller Pattern.
 * @see Command
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public class BlockUserCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException, PersistentException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = userService.selectById(((User)request.getSession().getAttribute("user")).getId());
        if (user.getRole() != Role.ADMIN) {
            request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request,response);
            return ;
        }
        User userToBlock = userService.selectById(Long.parseLong(request.getParameter("id")));
        userToBlock.setBanned(!userToBlock.isBanned());
        userService.update(userToBlock);
        request.getSession().setAttribute("user", user);
        response.sendRedirect((String) request.getSession().getAttribute("prev_link"));
    }
}
