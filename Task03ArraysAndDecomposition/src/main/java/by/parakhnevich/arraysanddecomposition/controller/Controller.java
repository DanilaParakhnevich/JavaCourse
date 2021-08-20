package by.parakhnevich.arraysanddecomposition.controller;

import by.parakhnevich.arraysanddecomposition.controller.command.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller accept list of need values and
 * use need command (class that implements Command) to
 * return String value for View-class
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.controller.command.Command
 * @see by.parakhnevich.arraysanddecomposition.view.View
 */

public class Controller {
    List<Command> listOfCommands= new ArrayList<>();
    private final int numberOfTask;
    private final int numberOfBean;
    private final String type;

    public Controller(int numberOfTask, int numberOfBean, String type){
        this.numberOfBean = numberOfBean;
        this.numberOfTask = numberOfTask;
        this.type = type;

        //Initialization of all the commands
        listOfCommands.add(new ShellCommand());
        listOfCommands.add(new InsertionCommand());
        listOfCommands.add(new BubbleCommand());
        listOfCommands.add(new SelectionCommand());
        listOfCommands.add(new CocktailCommand());
        listOfCommands.add(new MergeCommand());
        listOfCommands.add(new SortingBigFileCommand());
        listOfCommands.add(new QuickCommand());
        listOfCommands.add(new SumOfMatricesCommand());
        listOfCommands.add(new DifferenceOfTwoMatricesCommand());
        listOfCommands.add(new MultiplicationOfTwoMatricesCommand());
        listOfCommands.add(new MultiplicationMatrixWithNumberCommand());
        listOfCommands.add(new TranspositionCommand());
    }

    public String doRequest(List<String> listOfValues) throws IOException {
        if (type != null) {
            listOfValues.add(type);
        }
        if (numberOfBean == 1) {
            return listOfCommands.get(numberOfTask - 1).execute(listOfValues);
        }
        return listOfCommands.get((numberOfBean-1) * 7 + numberOfTask).execute(listOfValues);
    }
}
