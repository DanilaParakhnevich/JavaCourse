package by.parakhnevich.arraysanddecomposition.controller.command;

import java.io.IOException;
import java.util.List;

/**
 * This method execute predetermined task number for
 * Controller that accept list of need values and
 * use need command (class that implements Command) to
 * return String value for View-class
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.controller.Controller
 * @see by.parakhnevich.arraysanddecomposition.controller.command <--there are all commands
 * @see by.parakhnevich.arraysanddecomposition.controller.parser <--there are all parsers that return need info
 */
public interface Command {

    public String execute(List<String> list) throws IOException;
}
