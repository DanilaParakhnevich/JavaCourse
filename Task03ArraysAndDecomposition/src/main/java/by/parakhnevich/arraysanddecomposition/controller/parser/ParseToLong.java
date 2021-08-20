package by.parakhnevich.arraysanddecomposition.controller.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Check Interface Parser
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.controller.parser.Parser
 */
public class ParseToLong implements Parser {
    @Override
    public List<Number> parse(List<String> list) {
        List<Number> result = new ArrayList<>();
        for (String line : list){
            result.add(Long.parseLong(line.trim()));
        }
        return result;
    }
}
