package by.parakhnevich.command;

import by.parakhnevich.utilloops.FunctionCalculator;

import java.util.List;

public class CalculateFunctionCommand implements Command{
    private static final String RESULT = "Result is %.4f";


    @Override
    public void execute(List<Number> list) {
        FunctionCalculator functionCalculator = new FunctionCalculator(list);
        functionCalculator.printFunction();
        System.out.println(String.format(RESULT,functionCalculator.calculate()));
    }
}
