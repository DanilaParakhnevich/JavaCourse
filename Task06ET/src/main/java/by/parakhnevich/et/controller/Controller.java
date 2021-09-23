package by.parakhnevich.et.controller;

import by.parakhnevich.et.controller.command.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    List<Command> listOfCommands;
    Logger logger = LogManager.getLogger(Controller.class);

    public Controller() {
        listOfCommands = new ArrayList<>();
        listOfCommands.add(new ShowListCommand());
        listOfCommands.add(new ResetListCommand());
        listOfCommands.add(new SaveChangesCommand());
        listOfCommands.add(new FindByRadiusCommand());
        listOfCommands.add(new FindWithLessRadiusCommand());
        listOfCommands.add(new FindWithBiggerRadiusCommand());
        listOfCommands.add(new FindByPointCommand());
        listOfCommands.add(new FindByIdCommand());
        listOfCommands.add(new SortViaIdCommand());
        listOfCommands.add(new SortViaRadiusCommand());
        listOfCommands.add(new SortViaXCoordinateCommand());
        listOfCommands.add(new SortViaYCoordinateCommand());
    }
    public String execute(List<String> message) {
        try {
            return listOfCommands.get(Integer.parseInt(message.remove(0))).execute(message);
        } catch (IOException e) {
            logger.error(e);
        }
        return "Error";
    }
}
