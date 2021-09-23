package by.parakhnevich.et.controller.command;

import java.io.IOException;
import java.util.List;

public interface Command {
    public String execute(List<String> list) throws IOException;
}
