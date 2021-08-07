package by.parakhnevich.branchingandloops.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Class {@code Object} parse information from string to double
 * in classes that implements Interface Command
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.branchingandloops.controller.command.Command
 */

public class Parser {
    private Parser(){}

    public static List<Double> parseListToDouble(List<String> list){
        List<Double> result = new ArrayList<>();
        for (String line : list){
            result.add(Double.parseDouble(line.trim()));
        }
        return result;
    }
}
