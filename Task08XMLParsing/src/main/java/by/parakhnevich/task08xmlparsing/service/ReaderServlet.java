package by.parakhnevich.task08xmlparsing.service;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;
import java.util.stream.Collectors;


@WebServlet(urlPatterns = "/reader")
@MultipartConfig(location = "J:\\Projects\\JavaCourse\\Task08XMLParsing\\src\\main\\resources\\files\\copies")
public class ReaderServlet extends HttpServlet {
    private static final String TYPE = "ParserType";
    private static final String FILE_NAME = "fileName";
    private static String lastFileName;
    private static String lastChooseType;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (Part part : req.getParts()) {
            if (part.getName().equals("xml")) {
                InputStream inputStream = part.getInputStream();
                InputStreamReader isr = new InputStreamReader(inputStream);
                new BufferedReader(isr)
                        .lines()
                        .collect(Collectors.joining("\n"));
            } else {
                if (part.getSubmittedFileName() != null) {
                    req.getSession().setAttribute(FILE_NAME, UUID.randomUUID() + part.getSubmittedFileName());
                    part.write(req.getSession().getAttribute(FILE_NAME).toString());
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/controller");
        req.getSession().setAttribute(TYPE, req.getParameter(TYPE));
        setLastChooseType(req.getParameter(TYPE));
    }

    public static String getLastFileName() {
        return lastFileName;
    }

    public static void setLastFileName(String lastFileName) {
        ReaderServlet.lastFileName = lastFileName;
    }

    public static String getLastChooseType() {
        return lastChooseType;
    }

    public static void setLastChooseType(String lastChooseType) {
        ReaderServlet.lastChooseType = lastChooseType;
    }
}
