package by.parakhnevich.utilloops;

import java.util.List;

public class CurrentOfPositiveNumbers {
    List<Number> list;

    public CurrentOfPositiveNumbers(List<Number> list){
        this.list = list;
    }

    public int count(){
        int current = 0;
        for (Number number : list){
            if (number.doubleValue() > 0){
                current++;
            }
        }
        return current;
    }
}
