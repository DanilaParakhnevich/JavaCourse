package by.parakhnevich.arraysanddecomposition.controller.parser;

import by.parakhnevich.arraysanddecomposition.bean.Matrix;

import java.util.List;

/**
 * Class {@code Object} parse array that recorded in list
 * from string to int (first 2 values are rows and columns)
 * @autor Danila Parakhnevich
 * @version 1.0
 */
public class ParseMatrix {
    private ParseMatrix() {}

    public static Matrix<Number> parseListToInteger(List<String> list, String type){
        List<Number> listOfInt = Parse.parseList(list, type);
        Matrix<Number> matrix = new Matrix<>(listOfInt.get(0).intValue(),listOfInt.get(1).intValue());
        listOfInt.remove(0);
        listOfInt.remove(0);
        for (int index1 = 0; index1 < matrix.getRows(); index1++) {
            for (int index2 = 0; index2 < matrix.getColumns(); index2++) {
                matrix.put(index1, index2,  listOfInt.remove(0));
            }
        }
        return matrix;
    }
}
