package by.parakhnevich.oop.controller;

import by.parakhnevich.oop.controller.command.*;
import by.parakhnevich.oop.controller.command.ReturnMaxDaysCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Part of command pattern
 * @autor Danila Parakhnevich
 * @version 1.1
 * @see by.parakhnevich.oop.controller.command.Command
 */
public class Controller {
    List<Command> listOfCommands;
    private static final Logger logger = (Logger)
            LogManager.getLogger(Controller.class.getName());
    public Controller() {

        listOfCommands = new ArrayList<>();
        listOfCommands.add(new ShowListCommand());
        listOfCommands.add(new ShowSortedViaCostCommand());
        listOfCommands.add(new ShowSortedViaMaxCountOfDaysCommand());
        listOfCommands.add(new ShowSortedViaCountryCommand());
        listOfCommands.add(new ShowSortedViaTypeCommand());
        listOfCommands.add(new SetAsDefaultCommand());
        listOfCommands.add(new ShowChooseCommand());
        listOfCommands.add(new ReturnMaxDaysCommand());
        listOfCommands.add(new ReturnSizeTransportCommand());
        listOfCommands.add(new ShowTransportListCommand());
        listOfCommands.add(new ReturnSizeFoodCommand());
        listOfCommands.add(new ShowFoodListCommand());
        listOfCommands.add(new LastShowCommand());
    }

    public String doRequest(List<String> list) {
        int number = Integer.parseInt(list.get(0));
        try {
            return listOfCommands.get(number - 1).execute(list);
        }
        catch (IOException e) {
            logger.error(e);
        }
        return null;
    }
}
