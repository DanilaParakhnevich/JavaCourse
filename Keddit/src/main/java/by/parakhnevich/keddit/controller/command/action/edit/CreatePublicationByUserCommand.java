package by.parakhnevich.keddit.controller.command.action.edit;

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

public class CreatePublicationByUserCommand implements Command {
    Logger logger = LogManager.getLogger(CreatePublicationByUserCommand.class);
    PhotoNameGenerator generator = new PhotoNameGenerator();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PublicationService publicationService = ServiceFactory.getInstance().getPublicationService();
        UserService userService = ServiceFactory.getInstance().getUserService();
        DateCreator dateCreator = new DateCreator();
        try {
            User user = (User) request.getSession().getAttribute("user");
            user = userService.selectById(user.getId());
            Publication publication = new Publication();
            publication.setUser(user);
            publication.setDate(Timestamp.valueOf(dateCreator.create().replaceAll("/", "-")));
            String fileName =  load(publication, request, user.getNickname());
            if (publication.getHeading().equals("")) {
                request.setAttribute("error_message_create_publication", "Heading must be");
                request.getRequestDispatcher(CommandPage.CREATE_PUBLICATION_BY_USER).forward(request, response);
            }
            if (publication.getTextContent().equals("")) {
                request.setAttribute("error_message_create_publication", "Body must be");
                request.getRequestDispatcher(CommandPage.CREATE_PUBLICATION_BY_USER).forward(request, response);
            }
            if (publication.getTags().get(0).equals("")) {
                request.setAttribute("error_message_create_publication", "Minimally one tag must be");
                request.getRequestDispatcher(CommandPage.CREATE_PUBLICATION_BY_USER).forward(request, response);
            }
            if (fileName.equals("")) {
                publication.setPhoto(null);
            }
            else {
                publication.setPhoto(new File(".src/main/webapp/photos/" + fileName));
            }
            publication.setOnModeration(true);
            publication.setId(publicationService.getFreeId());
            publicationService.add(publication);
            request.setAttribute("publications", publicationService.selectAll());
            request.setAttribute("user",user);
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher(CommandPage.PUBLICATIONS).forward(request, response);
        } catch (ServiceException | TransactionException e) {
            logger.error(e);
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
            if (part.getName().equals("body")) {
                publication.setTextContent(convertStreamToString(part.getInputStream()));
            }
            if (part.getName().equals("head")) {
                publication.setHeading(convertStreamToString(part.getInputStream()));
            }
            if (part.getName().equals("tags")) {
                String tags = convertStreamToString(part.getInputStream());
                for (String tag : tags.trim().split(",")) {
                    publication.addTag(tag);
                }
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

    private String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    private static String getFileExtension(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return "." + fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
