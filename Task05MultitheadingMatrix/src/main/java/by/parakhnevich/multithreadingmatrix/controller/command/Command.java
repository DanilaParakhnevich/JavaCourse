package by.parakhnevich.multithreadingmatrix.controller.command;

import by.parakhnevich.multithreadingmatrix.dao.exception.DAOException;

import java.io.IOException;
import java.util.List;

public interface Command {
    public String execute(List<String> list) throws InterruptedException, IOException, DAOException;
}
