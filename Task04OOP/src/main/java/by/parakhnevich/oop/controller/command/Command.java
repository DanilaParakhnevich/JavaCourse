package by.parakhnevich.oop.controller.command;

import java.io.IOException;
import java.util.List;

/**
 * This interface is part of command pattern
 * @autor Danila Parakhnevich
 * @version 1.1
 * @see by.parakhnevich.oop.controller.Controller
 */
public interface Command {
    public java.lang.String execute(List<String> list) throws IOException;
}
