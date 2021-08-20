package by.parakhnevich.arraysanddecomposition.controller.command;

import by.parakhnevich.arraysanddecomposition.bean.Matrix;
import by.parakhnevich.arraysanddecomposition.controller.parser.ParseMatrix;
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
public class MultiplicationMatrixWithNumberCommand implements Command{
    @Override
    public String execute(List<String> list) throws FileNotFoundException {
        Matrix<Number> matrix = ParseMatrix.parseListToInteger(list,list.remove(list.size()-1));
        return LocaleSingleton.getResourceBundle().getString("MATRIX") + '\n'
                + matrix.get() + '\n' + LocaleSingleton.getResourceBundle().getString("NUMBER") +
                '\n' + list.get(list.size()-1) + '\n' +
                LocaleSingleton.getResourceBundle().getString("RESULT")
                + '\n' + new MatrixCalculator<>().multiply(matrix ,
               Integer.parseInt(list.get(list.size()-1))).get();
    }
}
