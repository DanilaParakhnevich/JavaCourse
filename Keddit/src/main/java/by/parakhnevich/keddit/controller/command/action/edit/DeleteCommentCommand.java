package by.parakhnevich.keddit.controller.command.action.edit;

import by.parakhnevich.keddit.bean.publication.Comment;
import by.parakhnevich.keddit.bean.user.Role;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.CommentService;
import by.parakhnevich.keddit.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class DeleteCommentCommand that is Command for
 * Controller Pattern.
 * @see Command
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public class DeleteCommentCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException, PersistentException {
        CommentService commentService = ServiceFactory.getInstance().getCommentService();
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = userService.selectById(((User)(request.getSession().getAttribute("user"))).getId());
        Comment comment = commentService.selectById(Long.parseLong(request.getParameter("id")));
        if (user.getId() != comment.getUser().getId() && user.getRole() != Role.ADMIN) {
            request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request, response);
            return;
        }
        commentService.delete(comment);
        if (comment.getPhoto() != null) {
            comment.getPhoto().delete();
        }
        request.getSession().setAttribute("user", user);
        response.sendRedirect((String) request.getSession().getAttribute("prev_link"));
    }
}
