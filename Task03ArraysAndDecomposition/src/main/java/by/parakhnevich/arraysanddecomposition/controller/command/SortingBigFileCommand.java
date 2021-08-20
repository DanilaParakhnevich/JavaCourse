package by.parakhnevich.arraysanddecomposition.controller.command;


import by.parakhnevich.arraysanddecomposition.service.utilarrays.SortingOfBigFile;
import by.parakhnevich.arraysanddecomposition.view.locale.LocaleSingleton;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Check Interface Command
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.controller.command.Command
 */
public class SortingBigFileCommand implements Command {
    @Override
    public String execute(List<String> list) throws IOException {
        new SortingOfBigFile().sort(new File(list.get(0)));
        return LocaleSingleton.getResourceBundle().getString("SUCCESS");
    }
}
