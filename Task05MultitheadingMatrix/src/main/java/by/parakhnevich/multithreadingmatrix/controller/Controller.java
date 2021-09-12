package by.parakhnevich.multithreadingmatrix.controller;

import by.parakhnevich.multithreadingmatrix.controller.command.*;
import by.parakhnevich.multithreadingmatrix.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    List<Command> listOfCommands = new ArrayList<>();
    Logger logger = (Logger) LogManager.getLogger(Controller.class);
    public Controller() {
        listOfCommands.add(new PuttingThreadsWithLockerCommand());
        listOfCommands.add(new PuttingThreadWithSemaphoreCommand());
        listOfCommands.add(new PuttingThreadWithCountDownLatchCommand());
        listOfCommands.add(new PuttingThreadsWithPhaserCommand());
        listOfCommands.add(new PuttingSimpleThreadsCommand());
    }

    public String execute(List<String> message) {
        try {
            return listOfCommands.get(Integer.parseInt(message.remove(0))).
                    execute(message);
        } catch (InterruptedException | IOException | DAOException e) {
            logger.error(e);
        }
        return null;
    }
}

