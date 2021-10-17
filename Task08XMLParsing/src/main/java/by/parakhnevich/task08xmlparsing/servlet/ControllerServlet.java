package by.parakhnevich.task08xmlparsing.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/")
public class ControllerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String encoding = "UTF-8";
        response.setCharacterEncoding(encoding);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<small>Ниже вы можете получить результаты разбора xml-файла 3 парсерами    </small>");
        out.println("<small>со сведениями о банках</small>");
        out.println("<form action=\"upload\" method=\"post\"/>");
        out.println("<enctype=\"multipart/form-data\">");
        out.println("<input type=\"text\" name=\"description\"/>");
        out.println("<input type=\"file\" name=\"file\"/>");
        out.println("<input type=\"submit\"/>");
        out.println("<select>" +
                "  <option>DOM Parser</option>" +
                "  <option>Sax Parser</option>" +
                " <option>StAX Parser</option>" +
                "</select>");
        out.println("</form>");
        out.println("<style>");
        out.println("body {" +
                "background: #c7b39b url(https://img1.akspic.ru/originals/0/7/1/1/21170-luna-utro-cifrovoe_iskusstvo-minimalizm-gorizont-1920x1080.jpg);}");
        out.println("</style>");
        out.println("</head>");
        out.println("</html>");
    }
}