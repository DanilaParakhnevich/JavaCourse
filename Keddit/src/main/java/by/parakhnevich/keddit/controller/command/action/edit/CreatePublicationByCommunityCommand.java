package by.parakhnevich.keddit.controller.command.action.edit;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.publication.Publication;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.dao.exception.TransactionException;
import by.parakhnevich.keddit.service.DateCreator;
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
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.stream.Collectors;

public class CreatePublicationByCommunityCommand implements Command {
    Logger logger = LogManager.getLogger(CreatePublicationByCommunityCommand.class);
    PhotoNameGenerator generator = new PhotoNameGenerator();
    DateCreator creator = new DateCreator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        User user = (User) request.getSession().getAttribute("user");
        UserService userService = ServiceFactory.getInstance().getUserService();
        PublicationService publicationService = ServiceFactory.getInstance().getPublicationService();
        user = userService.selectById(user.getId());
        Community community = new Community();
        community.setId(Long.parseLong(request.getParameter("id")));
        Publication publication = new Publication();
        publication.setHeading(request.getParameter("head"));
        publication.setTextContent(request.getParameter("body"));
        for (String tag : request.getParameter("tags").split(",")) {
            publication.getTags().add(tag.trim());
        }
        String name = generator.generate(community.getName(), "");
        String fileName =  load(name, request);
        if (publication.getHeading().equals("")) {
            request.setAttribute("error_message_create_publication", "HEAD_ERROR");
            request.getRequestDispatcher(CommandPage.CREATE_PUBLICATION_BY_COMMUNITY).forward(request, response);
            return;
        }
        if (publication.getTextContent().equals("")) {
            request.setAttribute("error_message_create_publication", "BODY_ERROR");
            request.getRequestDispatcher(CommandPage.CREATE_PUBLICATION_BY_COMMUNITY).forward(request, response);
            return;
        }
        if (publication.getTags().get(0).equals("")) {
            request.setAttribute("error_message_create_publication", "TAG_ERROR");
            request.getRequestDispatcher(CommandPage.CREATE_PUBLICATION_BY_COMMUNITY).forward(request, response);
            return;
        }
        try {
            if (!fileName.contains(".")) {
                publication.setPhoto(null);
            }
            else {
                publication.setPhoto(new File(".src/main/webapp/photos/" + fileName));
            }
            publication.setId(publicationService.getFreeId());
            publication.setDate(Timestamp.valueOf(creator.create().replaceAll("/", "-")));
            publication.setUser(user);
            publication.setCommunityOwner(community);
            publication.setOnModeration(true);
            publicationService.add(publication);
            request.setAttribute("publications", publicationService.selectAll());
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher(CommandPage.PUBLICATIONS).forward(request, response);
        } catch (ServiceException | TransactionException e) {
            request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request, response);
            logger.error(e);
        }
    }

    private String load(String name, HttpServletRequest request) throws ServletException, IOException {
        Iterator<Part> var3 = request.getParts().iterator();
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
        return result;
    }

    private static String getFileExtension(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return "." + fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
