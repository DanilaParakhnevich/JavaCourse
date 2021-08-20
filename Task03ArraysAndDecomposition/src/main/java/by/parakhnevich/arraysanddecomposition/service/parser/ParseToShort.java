package by.parakhnevich.arraysanddecomposition.service.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Check Interface Parser
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.service.parser.Parser
 */
public class ParseToShort implements Parser {

    @Override
    public List<Number> parse(List<String> list) {
        List<Number> result = new ArrayList<>();
        for (String line : list){
            result.add(Byte.parseByte(line.trim()));
        }
        return result;
    }
}
