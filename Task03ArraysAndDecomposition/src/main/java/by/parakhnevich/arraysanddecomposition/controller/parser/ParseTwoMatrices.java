package by.parakhnevich.arraysanddecomposition.controller.parser;

import by.parakhnevich.arraysanddecomposition.bean.Matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Class {@code Object} is upgraded version of
 * ParserMatrix that can parse 2 matrices
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.controller.parser.ParseMatrix
 */
public class ParseTwoMatrices {
    private ParseTwoMatrices() {}

    public static List<Matrix<Number>> parseListToInteger(List<String> list, String type){
        List<Matrix<Number>> matrixList = new ArrayList<>();
        List<Number> listOfInt = Parse.parseList(list, type);
        for (int index = 0; index < 2; index++) {
            matrixList.add(new Matrix<>(listOfInt.get(0).intValue(), listOfInt.get(1).intValue()));
            listOfInt.remove(0);
            listOfInt.remove(0);
            for (int index1 = 0; index1 < matrixList.get(0).getRows(); index1++) {
                for (int index2 = 0; index2 < matrixList.get(0).getColumns(); index2++) {
                    matrixList.get(index).put(index1, index2, listOfInt.remove(0));
                }
            }
        }
        return matrixList;
    }
}

