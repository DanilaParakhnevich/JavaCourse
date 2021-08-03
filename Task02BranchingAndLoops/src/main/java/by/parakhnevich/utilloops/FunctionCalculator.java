package by.parakhnevich.utilloops;

import java.util.List;

import static java.lang.Math.pow;

public class FunctionCalculator {
    List<Number> list;
    private static final String INFO = "Function:" +
            "\n" + "x >= 8 : - x^2  + x - 9" +
            "\n" + "x < 8 : 1 / ( x^4 - 6 )";

    public FunctionCalculator(List<Number> list){
        this.list = list;
    }

    public double calculate(){
        double x = list.get(0).doubleValue();
        if (x >= 8){
            return (-pow(x,2) + x - 9);
        }
        return 1.0/(pow(x,4) - 6);
    }

    public void printFunction(){
        System.out.println(INFO);
    }
}
