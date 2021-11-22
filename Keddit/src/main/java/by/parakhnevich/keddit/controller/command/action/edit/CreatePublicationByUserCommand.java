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
import by.parakhnevich.keddit.service.impl.UserServiceImpl;
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
        UserService userService = new UserServiceImpl();
        request.getSession().removeAttribute("type");
        DateCreator dateCreator = new DateCreator();
        try {
            User user = (User) request.getSession().getAttribute("user");
            user = userService.selectById(user.getId());
            Publication publication = new Publication();
            publication.setUser(user);
            publication.setDate(Timestamp.valueOf(dateCreator.create().replaceAll("/", "-")));
            publication.setTextContent(request.getParameter("body"));
            publication.setHeading(request.getParameter("head"));
            publication.getTags().add(request.getParameter("tags"));
            String fileName =  load(request, user.getNickname());
            if (!fileName.contains(".")) {
                user.setPhoto(null);
            }
            else {
                user.setPhoto(new File(".src/main/webapp/photos/" + fileName));
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
            if (part.getSubmittedFileName() != null) {
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
