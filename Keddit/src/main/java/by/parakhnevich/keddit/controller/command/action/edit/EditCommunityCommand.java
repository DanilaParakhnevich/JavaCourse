package by.parakhnevich.keddit.controller.command.action.edit;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.service.PhotoNameGenerator;
import by.parakhnevich.keddit.service.ServiceFactory;
import by.parakhnevich.keddit.service.exception.ServiceException;
import by.parakhnevich.keddit.service.interfaces.CommunityService;
import by.parakhnevich.keddit.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * The class EditCommunityCommand that is Command for
 * Controller Pattern.
 * @see Command
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public class EditCommunityCommand implements Command {
    private final PhotoNameGenerator generator = new PhotoNameGenerator();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException, PersistentException {
        CommunityService communityService = ServiceFactory.getInstance().getCommunityService();
        UserService userService = ServiceFactory.getInstance().getUserService();
        Community community = communityService.selectById(Long.parseLong(request.getParameter("id")));
        User user = userService.selectById(((User)request.getSession().getAttribute("user")).getId());
        if (user.getId() != community.getUser().getId() && !user.getRole().toString().equals("ADMIN")) {
            request.getRequestDispatcher(CommandPage.ERROR_PAGE).forward(request, response);
            return;
        }
        community.setName(request.getParameter("name"));
        String fileName =  load(request, user.getNickname());
        if (community.getName().equals("") && fileName.equals("")) {
            request.setAttribute("error_message_edit_community", "COMMUNITY_EDIT_ERROR");
            request.getRequestDispatcher(CommandPage.EDIT_COMMUNITY).forward(request, response);
            return;
        }
        if (!fileName.equals("")) {
            if (community.getPhoto() != null) {
                community.getPhoto().delete();
            }
            community.setPhoto(new File(".src/main/webapp/photos/" + fileName));
        }
        communityService.update(community);
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


    private static String getFileExtension(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return "." + fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
