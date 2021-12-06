package by.parakhnevich.keddit.controller.command.action.redirect;

import by.parakhnevich.keddit.controller.command.Command;
import by.parakhnevich.keddit.controller.command.CommandPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class RegistrationCommandPage that is Command for
 * Controller Pattern.
 * @see Command
 * @see by.parakhnevich.keddit.controller.command.CommandProvider
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public class RegistrationCommandPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(CommandPage.REGISTRATION_PAGE).forward(request,response);
    }
}
