package by.parakhnevich.keddit.controller.command.action.post;

import by.parakhnevich.keddit.bean.user.Role;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.exception.ServiceException;
import by.parakhnevich.keddit.exception.TransactionException;
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
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        String mail = request.getParameter("mail");
        try {
            User user = new User();
            user.setNickname(nickname);
            user.setMail(mail);
            user.setPassword(passwordService.encrypt(password));
            user.setPhoto(new File("src/main/webapp/photos/" + load(request, nickname)));
            user.setId(userService.getFreeId());
            user.setDate(Timestamp.valueOf(creator.create().replaceAll("/", "-")));
            user.setRole(Role.USER);
            userService.add(user);
            request.setAttribute("publications", publicationService.selectAll());
            request.setAttribute("user",user);
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
        return result;
    }

    private static String getFileExtension(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return "." + fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
