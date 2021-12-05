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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCommentCommand implements Command {
   Logger logger = LogManager.getLogger(DeleteCommentCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        CommentService commentService = ServiceFactory.getInstance().getCommentService();
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = userService.selectById(((User)(request.getSession().getAttribute("user"))).getId());
        Comment comment = commentService.selectById(Long.parseLong(request.getParameter("id")));
        if (user.getId() != comment.getUser().getId() && user.getRole() != Role.ADMIN) {
            request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request, response);
            return;
        }
        try {
            commentService.delete(comment);
            if (comment.getPhoto() != null) {
                comment.getPhoto().delete();
            }
        } catch (PersistentException e) {
            logger.error(e);
        }
        request.getSession().setAttribute("user", user);
        response.sendRedirect((String) request.getSession().getAttribute("prev_link"));
    }
}
