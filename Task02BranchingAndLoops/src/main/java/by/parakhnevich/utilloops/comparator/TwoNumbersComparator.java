package by.parakhnevich.utilloops.comparator;

import java.util.List;

public class TwoNumbersComparator {
    List<Number> list;

    public TwoNumbersComparator(List<Number> list){
        this.list = list;
    }

    public boolean compare(){
        return list.get(0).equals(list.get(1));
    }
}
