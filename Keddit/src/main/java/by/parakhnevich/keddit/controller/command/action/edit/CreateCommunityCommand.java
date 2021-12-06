package by.parakhnevich.keddit.controller.command.action.edit;

import by.parakhnevich.keddit.bean.publication.Community;
import by.parakhnevich.keddit.bean.user.User;
import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;
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
 * The class CreateCommunityCommand that is Command for
 * Controller Pattern.
 * @see Command
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public class CreateCommunityCommand implements Command {
    private final PhotoNameGenerator generator = new PhotoNameGenerator();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServiceException {
        CommunityService communityService = ServiceFactory.getInstance().getCommunityService();
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = (User) request.getSession().getAttribute("user");
        user = userService.selectById(user.getId());
        Community community = new Community();
        community.setUser(user);
        community.setName(request.getParameter("name"));
        String fileName =  load(request, user.getNickname());
        if (community.getName().equals("")) {
            request.setAttribute("error_message_create_community", "HEAD_ERROR");
            request.getRequestDispatcher(CommandPage.CREATE_COMMUNITY).forward(request, response);
            return;
        }
        if (fileName.equals("")) {
            community.setPhoto(null);
        }
        else {
            community.setPhoto(new File(".src/main/webapp/photos/" + fileName));
        }
        community.setId(communityService.getFreeId());
        communityService.add(community);
        communityService.addFollower(community, user);
        request.getSession().setAttribute("user", user);
        response.sendRedirect("/keddit.by/controller?command=user_communities&id=" + user.getId());
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
