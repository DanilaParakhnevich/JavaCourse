package by.parakhnevich.branchingandloops.utilloops;


import java.awt.*;
import java.util.List;

/**
 * Class {@code Object} define are 3 points on 1 line
 * TASK03
 * @autor Danila Parakhnevich
 * @version 1.0
 */
public class ArePointsOnLine {
    private final List<Double> list;

    public ArePointsOnLine(List<Double> list){
        this.list = list;
    }

    public boolean define(){
        Point firstPoint = new Point(list.get(0).intValue(),list.get(1).intValue());
        Point secondPoint = new Point(list.get(2).intValue(),list.get(3).intValue());
        Point thirdPoint = new Point(list.get(4).intValue(),list.get(5).intValue());

        return (thirdPoint.getX() - firstPoint.getX()) * (secondPoint.getY() - firstPoint.getY())
                == (thirdPoint.getY() - firstPoint.getY())*(secondPoint.getX() - firstPoint.getX());
    }
}
