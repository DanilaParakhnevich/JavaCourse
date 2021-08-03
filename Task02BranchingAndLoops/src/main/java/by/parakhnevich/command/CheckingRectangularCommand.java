package by.parakhnevich.command;

import by.parakhnevich.bean.Triangle;
import by.parakhnevich.utilloops.TriangleDefinator;

import java.util.List;

public class CheckingRectangularCommand implements Command{
    private static final String RESULT_FIRST = "The possibility of a triangle : %b";
    private static final String RESULT_SECOND = "The possibility of a rectangular triangle : %b";


    @Override
    public void execute(List<Number> list) {
        Triangle triangle = new Triangle(list);
        TriangleDefinator triangleDefinator = new TriangleDefinator(triangle);
        System.out.println(String.format(RESULT_FIRST,triangleDefinator.isTriangle()));
        if (triangleDefinator.isTriangle()){
            System.out.println(String.format(RESULT_SECOND,triangleDefinator.isRectangular()));
        }
    }
}
