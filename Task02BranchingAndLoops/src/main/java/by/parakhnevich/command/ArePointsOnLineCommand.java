package by.parakhnevich.command;

import by.parakhnevich.utilloops.ArePointsOnLine;

import java.util.List;

public class ArePointsOnLineCommand implements Command{
    private static final String RESULT = "Result of definition is %b";


    @Override
    public void execute(List<Number> list) {
        ArePointsOnLine arePointsOnLine = new ArePointsOnLine(list);
        System.out.println(String.format(RESULT,arePointsOnLine.define()));
    }
}
