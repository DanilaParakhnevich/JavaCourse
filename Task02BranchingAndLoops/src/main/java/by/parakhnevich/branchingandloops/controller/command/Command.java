package by.parakhnevich.branchingandloops.controller.command;

import java.util.List;
/**
 * This method execute predetermined task number for
 * Controller - class
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.branchingandloops.controller.Controller
 */
public interface Command {
    public String execute(List<String> list);
}
