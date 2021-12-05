package by.parakhnevich.keddit.controller.command.action.edit;

import by.parakhnevich.keddit.bean.user.Role;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.dao.exception.TransactionException;
import by.parakhnevich.keddit.service.DateCreator;
import by.parakhnevich.keddit.service.PasswordService;
import by.parakhnevich.keddit.service.PhotoNameGenerator;
import by.parakhnevich.keddit.service.ServiceFactory;
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
import java.util.List;
import java.util.stream.Collectors;

public class SignUpCommand implements Command {
    Logger logger = LogManager.getLogger(SignUpCommand.class);
    PhotoNameGenerator generator = new PhotoNameGenerator();
    DateCreator creator = new DateCreator();
    PasswordService passwordService = new PasswordService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        PublicationService publicationService = ServiceFactory.getInstance().getPublicationService();
        User user1 = new User();
        String nickname = request.getParameter("nickname");
        user1.setNickname(nickname);
        String name = generator.generate(nickname, "");
        user1.setPassword(passwordService.encrypt(request.getParameter("password")));
        user1.setMail(request.getParameter("mail"));
        String fileName =  load(name, request);
        if (user1.getNickname().equals("") || user1.getPassword().equals("") || user1.getMail().equals("")) {
            request.setAttribute("error_message_sign_up", "All text fields are required");
            request.getRequestDispatcher(CommandPage.REGISTRATION_PAGE).forward(request, response);
            return;
        }
        try {
            List<User> users = userService.selectAll();
            for (User user : users) {
                if (user.getNickname().equals(user1.getNickname())) {
                    request.setAttribute("error_message_sign_up", "User with this nickname is already exist");
                    request.getRequestDispatcher(CommandPage.REGISTRATION_PAGE).forward(request, response);
                    return;
                }
                else if (user.getMail().equals(user1.getMail())) {
                    request.setAttribute("error_message_sign_up", "User with this mail is already exist");
                    request.getRequestDispatcher(CommandPage.REGISTRATION_PAGE).forward(request, response);
                    return;
                }
            }
            if (!fileName.contains(".")) {
                user1.setPhoto(null);
            }
            else {
                user1.setPhoto(new File(".src/main/webapp/photos/" + fileName));
            }
            user1.setId(userService.getFreeId());
            user1.setDate(Timestamp.valueOf(creator.create().replaceAll("/", "-")));
            user1.setRole(Role.USER);
            userService.add(user1);
            request.setAttribute("publications", publicationService.selectAll());
            request.getSession().setAttribute("user", user1);
            request.getRequestDispatcher(CommandPage.PUBLICATIONS).forward(request, response);
        } catch (ServiceException | TransactionException e) {
            logger.error(e);
            request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request, response);
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
