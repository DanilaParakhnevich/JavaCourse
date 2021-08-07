package by.parakhnevich.branchingandloops.controller;

import by.parakhnevich.branchingandloops.controller.command.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Class {@code Object} accept info from class View
 * and return info based on request
 * (From classes with interface Command)
 * @autor Danila Parakhnevich
 * @version 2.1
 * @see by.parakhnevich.branchingandloops.view.View
 * @see by.parakhnevich.branchingandloops.controller.command.Command
 */
public class Controller {
    private final int numberOfTask;
    private final List<Command> list;
    private final Logger logger = LogManager.getLogger(Controller.class.getName());

    // Constructor accept number of task and init all the commands
    public Controller(int number){
        numberOfTask = number;
        list = new ArrayList<>();
        list.add(new NumberOfPositiveNumbersCommand());
        list.add(new IsOneNumberEqualsAnotherCommand());
        list.add(new ArePointsOnLineCommand());
        list.add(new CheckingRectangularCommand());
        list.add(new CalculateFunctionCommand());
        list.add(new SumOfSeriesCommand());
        list.add(new PrintNumbersCommand());
        list.add(new CommonPartOfTwoNumbersCommand());
        list.add(new SumOfSimpleSeriesCommand());
        list.add(new MagicNumberCommand());
    }

    //Method accept from view listOfValues and causes
    //execute method of classes with interface Command
    public String doRequest(List<String> listOfValues){
        logger.info("Doing request");
        return list.get(numberOfTask-1).execute(listOfValues);
    }

}
