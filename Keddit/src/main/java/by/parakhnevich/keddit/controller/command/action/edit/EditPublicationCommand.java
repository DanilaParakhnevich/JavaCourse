package by.parakhnevich.keddit.controller.command.action.edit;

import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.service.PhotoNameGenerator;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.PublicationService;
import by.parakhnevich.keddit.service.interfaces.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class EditPublicationCommand implements Command {
    Logger logger = LogManager.getLogger(EditPublicationCommand.class);
        PhotoNameGenerator generator = new PhotoNameGenerator();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        PublicationService publicationService = ServiceFactory.getInstance().getPublicationService();
        UserService userService = ServiceFactory.getInstance().getUserService();
        Publication publication = publicationService.selectById(Long.parseLong(request.getParameter("id")));
        User user = userService.selectById(((User)request.getSession().getAttribute("user")).getId());
        try {
            if (user.getId() != publication.getUser().getId() && !user.getRole().toString().equals("ADMIN")) {
                request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request, response);
                return;
            }
            publication.setTextContent(request.getParameter("body"));
            publication.setHeading(request.getParameter("head"));
            String tags = request.getParameter("tags");
            String fileName =  load(publication, request, user.getNickname());
            if (publication.getHeading().equals("") && publication.getTextContent().equals("") && tags.equals("") && fileName.equals("")) {
                request.setAttribute("error_message_create_publication", "COMMUNITY_EDIT_ERROR");
                request.setAttribute("publication", publication);
                request.getRequestDispatcher(CommandPage.EDIT_PUBLICATION).forward(request, response);
                return;
            }
            if (fileName.equals("")) {
                publication.setPhoto(null);
            }
            else {
                publication.setPhoto(new File("D:\\Projects\\JavaCourse\\Keddit\\src\\main\\webapp\\photos\\" + fileName));
            }
            publication.setTags(new ArrayList<>());
            for (String tag : tags.trim().split(",")) {
                publication.addTag(tag);
            }

            publicationService.update(publication);
            request.getSession().setAttribute("user", user);
            response.sendRedirect((String) request.getSession().getAttribute("prev_link"));
        } catch (PersistentException e) {
            logger.error(e);
            request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request, response);
        }
    }

    private String load(Publication publication, HttpServletRequest request, String keyWord) throws ServletException, IOException {
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
                if (publication.getPhoto() != null) {
                    publication.getPhoto().delete();
                }
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
