package by.parakhnevich.arraysanddecomposition.controller.command;

import by.parakhnevich.arraysanddecomposition.bean.Matrix;
import by.parakhnevich.arraysanddecomposition.controller.parser.ParseTwoMatrices;
import by.parakhnevich.arraysanddecomposition.utilmatrix.MatrixCalculator;
import by.parakhnevich.arraysanddecomposition.view.locale.LocaleSingleton;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Check Interface Command
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.controller.command.Command
 */
public class DifferenceOfTwoMatricesCommand implements Command{
    @Override
    public String execute(List<String> list) throws FileNotFoundException {
        List<Matrix<Number>> listOfMatrices = ParseTwoMatrices.parseListToInteger(list,list.remove(list.size()-1));
        return LocaleSingleton.getResourceBundle().getString("FIRST_MATRIX") + '\n'
                + listOfMatrices.get(0).get() + '\n' +
                LocaleSingleton.getResourceBundle().getString("SECOND_MATRIX")
                + '\n' + listOfMatrices.get(1).get() + '\n' + LocaleSingleton.getResourceBundle().getString("RESULT")
                + '\n' +
                new MatrixCalculator<Number>().difference(listOfMatrices.get(0),listOfMatrices.get(1)).get();
    }
}
