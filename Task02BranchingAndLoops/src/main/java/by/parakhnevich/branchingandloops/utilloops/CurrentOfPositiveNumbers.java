package by.parakhnevich.branchingandloops.utilloops;

import java.util.List;

/**
 * Class {@code Object} accept list of numbers and
 * return number of positive of them
 * TASK01
 * @autor Danila Parakhnevich
 * @version 1.0
 */
public class CurrentOfPositiveNumbers {
    List<Double> list;

    public CurrentOfPositiveNumbers(List<Double> list){
        this.list = list;
    }

    public int count(){
        int current = 0;
        for (Double number : list){
            if (number > 0){
                current++;
            }
        }
        return current;
    }
}
