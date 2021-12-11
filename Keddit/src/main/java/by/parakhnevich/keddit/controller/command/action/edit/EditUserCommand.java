package by.parakhnevich.keddit.controller.command.action.edit;

import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.dao.exception.TransactionException;
import by.parakhnevich.keddit.service.PhotoNameGenerator;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.UserService;
import org.apache.logging.log4j.core.util.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class Login that is Command for
 * Controller Pattern.
 * @see Command
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public class EditUserCommand implements Command {
    private final PhotoNameGenerator generator = new PhotoNameGenerator();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException, TransactionException, PersistentException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = userService.selectById(( (User) request.getSession().getAttribute("user")).getId());
        String mail = request.getParameter("mail");
        String nickname = request.getParameter("nickname");
        if (!mail.equals("")) {
            user.setMail(mail);
        }
        if (!nickname.equals("")) {
            if (user.getNickname().length() > 20) {
                request.setAttribute("error_message_sign_up", "REG_PROBLEM_LENGTH");
                request.getRequestDispatcher(CommandPage.EDIT_USER).forward(request, response);
                return;
            }
            user.setNickname(nickname);
        }
        List<User> users;
        users = userService.selectAll();
        for (User user1 : users) {
            if (nickname.equals(user1.getNickname())) {
                request.setAttribute("error_message_edit_user", "User with this nickname is already exist");
                request.getRequestDispatcher(CommandPage.EDIT_USER).forward(request, response);
                return;
            }
            else if (mail.equals(user1.getMail())) {
                request.setAttribute("error_message_edit_user", "User with this mail is already exist");
                request.getRequestDispatcher(CommandPage.EDIT_USER).forward(request, response);
                return;
            }
        }
        String fileName =  load(request, user.getNickname());
        if (nickname.equals("") && mail.equals("") && fileName.equals("")) {
            request.setAttribute("error_message_edit_user", "COMMUNITY_EDIT_ERROR");
            request.getRequestDispatcher(CommandPage.EDIT_USER).forward(request, response);
            return;
        }
        if (!fileName.equals("")) {
            user.getPhoto().delete();
            user.setPhoto(new File(".src/main/webapp/photos/" + fileName));
        }
        userService.update(user);
        request.getSession().setAttribute("user", user);
        response.sendRedirect("/keddit.by/controller?command=publications");
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

    private String toString(InputStreamReader inputStream) {
        try {
            return IOUtils.toString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getFileExtension(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return "." + fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
