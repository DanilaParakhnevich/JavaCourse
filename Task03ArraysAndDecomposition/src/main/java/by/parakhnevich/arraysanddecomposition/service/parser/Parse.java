package by.parakhnevich.arraysanddecomposition.service.parser;

import java.util.List;

/**
 * Class {@code Object} parse information from string to type
 * that user picked
 * in classes that implements Interface Command
 * @autor Danila Parakhnevich
 * @version 1.0
 */
public class Parse {
    private Parse(){}

    public static List<Number> parseList(List<String> list, String type){
        Parser parser = new GetParser().getParser(type);
        return parser.parse(list);
    }
}
