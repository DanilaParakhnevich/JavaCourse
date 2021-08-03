package by.parakhnevich.controller;

import by.parakhnevich.command.*;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final int numberOfTask;
    private final List<Command> list;
    private final List<Number> listOfValues;

    public Controller(int number , List<Number> listOfValues){
        numberOfTask = number;
        this.listOfValues = listOfValues;

        list = new ArrayList<>();
        list.add(new NumberOfPositiveNumbersCommand());
        list.add(new IsOneNumberEqualsAnotherCommand());
        list.add(new ArePointsOnLineCommand());
        list.add(new CheckingRectangularCommand());
        list.add(new CalculateFunctionCommand());
    }

    public void doRequest(){
        list.get(numberOfTask-1).execute(listOfValues);
    }

}
