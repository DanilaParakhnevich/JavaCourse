package by.parakhnevich.keddit.controller;

import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandProvider;
import by.parakhnevich.keddit.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
@MultipartConfig(location = "D:\\Projects\\JavaCourse\\Keddit\\src\\main\\webapp\\photos\\")
public class KedditController extends HttpServlet {
    static Logger logger = LogManager.getLogger(KedditController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (ServiceException e) {
            logger.error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (ServiceException e) {
            logger.error(e);
        }
    }

    private static void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        String commandName = req.getParameter("command");
        logger.debug(commandName);
        Command command = CommandProvider.getInstance().getCommand(commandName);
        command.execute(req, resp);
    }
}
