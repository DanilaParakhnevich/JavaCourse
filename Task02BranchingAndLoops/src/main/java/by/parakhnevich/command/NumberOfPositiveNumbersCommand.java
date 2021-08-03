package by.parakhnevich.command;

import by.parakhnevich.utilloops.CurrentOfPositiveNumbers;


import java.util.List;

public class NumberOfPositiveNumbersCommand implements Command{
    private static final String RESULT = "Current of positive numbers is %d";

    @Override
    public void execute(List<Number> list) {
        CurrentOfPositiveNumbers currentOfPositiveNumbers = new CurrentOfPositiveNumbers(list);
        System.out.println(String.format(RESULT,currentOfPositiveNumbers.count()));
    }
}
