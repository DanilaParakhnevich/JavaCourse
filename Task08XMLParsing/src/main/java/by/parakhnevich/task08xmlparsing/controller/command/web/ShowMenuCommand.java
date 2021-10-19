package by.parakhnevich.task08xmlparsing.controller.command.web;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowMenuCommand implements WebCommand{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/jsp/main.jsp").forward(req, resp);
    }
}
