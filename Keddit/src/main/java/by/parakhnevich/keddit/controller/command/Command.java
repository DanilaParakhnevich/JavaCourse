package by.parakhnevich.keddit.controller.command;

import by.parakhnevich.keddit.dao.exception.PersistentException;
import by.parakhnevich.keddit.dao.exception.TransactionException;
import by.parakhnevich.keddit.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The interface Command is the part of Controller
 * pattern.
 * @see by.parakhnevich.keddit.controller.KedditController
 * @author Danila Parakhnevich
 */
public interface Command {
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, ServiceException, PersistentException, TransactionException;
}
