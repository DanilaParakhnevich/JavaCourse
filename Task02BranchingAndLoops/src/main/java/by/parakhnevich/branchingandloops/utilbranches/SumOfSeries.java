package by.parakhnevich.branchingandloops.utilbranches;

import by.parakhnevich.branchingandloops.bean.Series;
import by.parakhnevich.branchingandloops.view.locale.LocaleSingleton;

import java.util.concurrent.TimeoutException;

import static java.lang.Math.pow;

/**
 * Class {@code Object} calculate sum of series members those are
 * (1/first^n) and (1/second^n) while first member + second member
 * >= EPSILON
 * TASK06
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.branchingandloops.bean.Series
 */
public class SumOfSeries {
    Series series;
    private final double EPSILON;

    public SumOfSeries(double first, double second, double EPSILON) {
        this.EPSILON = EPSILON;
        this.series = new Series(first,second);
    }

    public SumOfSeries(double EPSILON) {
        this.EPSILON = EPSILON;
        this.series = new Series(2 , 3);
    }

    public double calculate(){
        try {
            if (EPSILON <= 0) {
                throw new TimeoutException("Bad EPSILON");
            }
            double sum = 0;
            int count = 1;
            double number = calculateMemberOfOrder(count++);
            while (number >= EPSILON){
                sum+=number;
                number = calculateMemberOfOrder(count++);
            }
            return sum;
        }
        catch (TimeoutException e) {
            e.printStackTrace();
        }
       return 0;
    }

    private double calculateMemberOfOrder(int n){
        return (1.0 / pow(series.getFirst(),n)) + (1.0 / pow(series.getSecond(),n));
    }

    public String showInfoAboutSeries(){
        return String.format(LocaleSingleton.getResourceBundle().getString("INFO_SERIES_1") +
                '\n' + LocaleSingleton.getResourceBundle().getString("INFO_SERIES_2"),series.getFirst(),series.getSecond(),EPSILON);
    }
}
