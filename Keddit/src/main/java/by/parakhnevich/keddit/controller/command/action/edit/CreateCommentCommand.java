package by.parakhnevich.keddit.controller.command.action.edit;

import by.parakhnevich.keddit.bean.publication.Comment;
import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.service.DateCreator;
import by.parakhnevich.keddit.service.PhotoNameGenerator;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.CommentService;
import by.parakhnevich.keddit.service.interfaces.PublicationService;
import by.parakhnevich.keddit.service.interfaces.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.stream.Collectors;

public class CreateCommentCommand implements Command {
    Logger logger = LogManager.getLogger(CreateCommentCommand.class);
    PhotoNameGenerator generator = new PhotoNameGenerator();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CommentService commentService = ServiceFactory.getInstance().getCommentService();
        UserService userService = ServiceFactory.getInstance().getUserService();
        DateCreator dateCreator = new DateCreator();
        PublicationService publicationService = ServiceFactory.getInstance().getPublicationService();
        try {
            User user = (User) request.getSession().getAttribute("user");
            user = userService.selectById(user.getId());
            Comment comment = new Comment();
            comment.setUser(user);
            comment.setDate(Timestamp.valueOf(dateCreator.create().replaceAll("/", "-")));
            comment.setContent(request.getParameter("body"));
            String fileName =  load(request, user.getNickname());
            if (comment.getContent().equals("")) {
                response.sendRedirect((String) request.getSession().getAttribute("prev_link"));
                return;
            }
            if (fileName.equals("")) {
                comment.setPhoto(null);
            }
            else {
                comment.setPhoto(new File(".src/main/webapp/photos/" + fileName));
            }
            comment.setId(commentService.getFreeId());
            Publication publication =
                    publicationService.selectById(((Publication) request.getSession().getAttribute("publication")).getId());
            commentService.add(comment, publication);
            request.getSession().setAttribute("user", user);
            response.sendRedirect((String) request.getSession().getAttribute("prev_link"));
        } catch (ServiceException e) {
            logger.error(e);
        }
    }

    private String load(HttpServletRequest request, String keyWord) throws ServletException, IOException {
        Iterator<Part> var3 = request.getParts().iterator();
        String name = generator.generate(keyWord, "");
        String result = "";
        while(var3.hasNext()) {
            Part part = var3.next();
            if (part.getName().equals("file")) {
                InputStream inputStream = part.getInputStream();
                InputStreamReader isr = new InputStreamReader(inputStream);
                (new BufferedReader(isr)).lines().collect(Collectors.joining("\n"));
            }
            if (part.getSubmittedFileName() != null && getFileExtension(part.getSubmittedFileName()).contains(".")) {
                part.write(name + getFileExtension(part.getSubmittedFileName()));
                result = name + getFileExtension(part.getSubmittedFileName());
            }
        }
        if (!result.contains(".")) {
            return "";
        }
        return result;
    }

    private static String getFileExtension(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return "." + fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
