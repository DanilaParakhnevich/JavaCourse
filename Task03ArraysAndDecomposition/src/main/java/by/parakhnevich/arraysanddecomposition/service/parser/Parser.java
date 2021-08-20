package by.parakhnevich.arraysanddecomposition.service.parser;

import java.util.List;

/**
 * Commands use parsers to parse need info
 * Parser accept info and parse it proceeding of its name
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.controller.command
 * @see by.parakhnevich.arraysanddecomposition.service.parser <--there are all parsers that return need info
 */
public interface Parser {
    public List<Number> parse(List<String> list);
}
