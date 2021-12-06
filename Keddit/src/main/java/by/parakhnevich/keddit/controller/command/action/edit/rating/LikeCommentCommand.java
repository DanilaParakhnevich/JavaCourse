package by.parakhnevich.keddit.controller.command.action.edit.rating;

import by.parakhnevich.keddit.bean.publication.Comment;
import by.parakhnevich.keddit.bean.publication.Dislike;
import by.parakhnevich.keddit.bean.publication.Like;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class LikeCommentCommand that is Command for
 * Controller Pattern.
 * @see Command
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public class LikeCommentCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        CommentService commentService = ServiceFactory.getInstance().getCommentService();
        User user = userService.selectById(((User) request.getSession().getAttribute("user")).getId());
        RatingFromCommentService ratingFromCommentService = ServiceFactory.getInstance().getRating();
        Comment comment = commentService.selectById(Long.parseLong(request.getParameter("id")));
        Like like = new Like();
        like.setUser(user);
        if (userService.hasDislikedComment(comment, user)) {
            System.out.println(21);
            Dislike dislike = new Dislike();
            dislike.setUser(user);
            ratingFromCommentService.delete(comment, dislike);
            ratingFromCommentService.add(comment, like);
        }
        else if (userService.hasLikedComment(comment, user)) {
            System.out.println(22);
            ratingFromCommentService.delete(comment, like);
        }
        else {
            System.out.println(23);
            ratingFromCommentService.add(comment, like);
        }
        request.getSession().setAttribute("user", user);
        response.sendRedirect(String.valueOf(request.getSession().getAttribute("prev_link")));
    }
}
