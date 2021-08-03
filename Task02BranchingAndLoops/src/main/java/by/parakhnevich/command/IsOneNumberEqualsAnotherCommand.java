package by.parakhnevich.command;

import by.parakhnevich.utilloops.comparator.TwoNumbersComparator;

import java.util.List;

public class IsOneNumberEqualsAnotherCommand  implements Command{
    private static final String RESULT = "Result of comparing : %b";


    @Override
    public void execute(List<Number> list) {
        TwoNumbersComparator twoNumbersComparator = new TwoNumbersComparator(list);
        System.out.println(String.format(RESULT,twoNumbersComparator.compare()));
    }
}
