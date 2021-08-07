package by.parakhnevich.branchingandloops.utilloops;

import by.parakhnevich.branchingandloops.view.locale.LocaleSingleton;

import java.util.List;

import static java.lang.Math.pow;

/**
 * Class {@code Object} calculate function
 * x >= 8 : - x^2  + x - 9
 * x < 8 : 1 / ( x^4 - 6 )
 * TASK05
 * @autor Danila Parakhnevich
 * @version 1.0
 */
public class FunctionCalculator {
    List<Double> list;

    public FunctionCalculator(List<Double> list){
        this.list = list;
    }

    public double calculate(){
        double x = list.get(0);
        if (x >= 8){
            return (-pow(x,2) + x - 9);
        }
        return 1.0/(pow(x,4) - 6);
    }

    public void printFunction(){
        System.out.println(LocaleSingleton.getResourceBundle().getString("INFO_FUNCTION")
                + '\n' + LocaleSingleton.getResourceBundle().getString("INFO_FUNCTION_1")
                + '\n' + LocaleSingleton.getResourceBundle().getString("INFO_FUNCTION_2"));
    }
}
