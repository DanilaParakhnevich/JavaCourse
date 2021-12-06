package by.parakhnevich.keddit.controller.command.action.edit.rating;

import by.parakhnevich.keddit.bean.publication.Dislike;
import by.parakhnevich.keddit.bean.publication.Like;
import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.PublicationService;
import by.parakhnevich.keddit.service.interfaces.RatingFromPublicationService;
import by.parakhnevich.keddit.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class DislikePublicationCommand that is Command for
 * Controller Pattern.
 * @see Command
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public class DislikePublicationCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        PublicationService publicationService = ServiceFactory.getInstance().getPublicationService();
        User user = userService.selectById(((User) request.getSession().getAttribute("user")).getId());
        RatingFromPublicationService ratingFromPublicationService =
                ServiceFactory.getInstance().getRatingFromPublicationService();
        Publication publication = publicationService.selectById(Long.parseLong(request.getParameter("id")));
        Dislike dislike = new Dislike();
        dislike.setUser(user);
        if (userService.hasLikedPublication(publication, user)) {
            Like like = new Like();
            like.setUser(user);
            ratingFromPublicationService.delete(publication, like);
            ratingFromPublicationService.add(publication, dislike);
        }
        else if (userService.hasDislikedPublication(publication, user)) {
            ratingFromPublicationService.delete(publication, dislike);
        }
        else {
            ratingFromPublicationService.add(publication, dislike);
        }
        request.getSession().setAttribute("user", user);
        response.sendRedirect(String.valueOf(request.getSession().getAttribute("prev_link")));
    }
}
