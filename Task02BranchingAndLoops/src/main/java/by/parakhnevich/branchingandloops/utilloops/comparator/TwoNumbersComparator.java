package by.parakhnevich.branchingandloops.utilloops.comparator;

import java.util.List;

/**
 * Class {@code Object} compare two double values
 * TASK02
 * @autor Danila Parakhnevich
 * @version 1.0
 */
public class TwoNumbersComparator {
    private final Double first;
    private final Double second;

    public TwoNumbersComparator(Double first,Double second){
        this.first = first;
        this.second = second;
    }

    public boolean compare(){
        return first.equals(second);
    }
}
