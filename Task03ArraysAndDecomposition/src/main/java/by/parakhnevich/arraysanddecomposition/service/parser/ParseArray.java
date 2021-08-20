package by.parakhnevich.arraysanddecomposition.service.parser;

import by.parakhnevich.arraysanddecomposition.bean.Array;

import java.util.List;

/**
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.service.parser.GetParser
 */
public class ParseArray {
    private ParseArray() {}

    public static Array<Number> parseListToArray(List<String> list, String type){
        return new Array<>(Parse.parseList(list, type));
    }
}
