package by.parakhnevich.branchingandloops.bean;

import by.parakhnevich.branchingandloops.view.locale.LocaleSingleton;

import java.util.Objects;


/**
 * Class {@code Object} serves as entity for calculate sum of series
 * a(n) = 1/first^n + 1/second^n;
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.branchingandloops.utilbranches.SumOfSeries
 */

public class Series {
    private final double first;
    private final double second;

    public Series(double first,double second){
        this.first = first;
        this.second = second;
    }

    public double getFirst() {
        return first;
    }

    public double getSecond(){
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Series series = (Series) o;
        return Double.compare(series.first, first) == 0 && Double.compare(series.second, second) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return String.format(LocaleSingleton.getResourceBundle().getString("INFO_SERIES"),
                first, second);
    }
}

