package by.parakhnevich.task08xmlparsing.controller;


import by.parakhnevich.task08xmlparsing.controller.command.web.ShowMenuCommand;
import by.parakhnevich.task08xmlparsing.controller.command.web.ShowResultCommand;
import by.parakhnevich.task08xmlparsing.controller.command.web.WebCommand;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(urlPatterns = "/controller")
@MultipartConfig(location = "J:/Projects/JavaCourse/Task08XMLParsing/src/main/resources/files")
public class ControllerServlet extends HttpServlet {
    private static final Map<String, WebCommand> commandMap = new HashMap<>();
    public ControllerServlet() {
        super();
        commandMap.put("result_page", new ShowResultCommand());
        commandMap.put("menu_page", new ShowMenuCommand());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String encoding = "UTF-8";
        response.setCharacterEncoding(encoding);
        try {
            commandMap.get(request.getSession().getAttribute("command")).execute(request, response);
        } catch (SAXException | ParserConfigurationException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


}