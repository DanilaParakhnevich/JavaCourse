package by.parakhnevich.arraysanddecomposition.controller.command;

import by.parakhnevich.arraysanddecomposition.bean.Array;
import by.parakhnevich.arraysanddecomposition.controller.parser.ParseArray;
import by.parakhnevich.arraysanddecomposition.utilarrays.MergeSorting;
import by.parakhnevich.arraysanddecomposition.view.locale.LocaleSingleton;

import java.util.List;

/**
 * Check Interface Command
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.controller.command.Command
 */
public class MergeCommand implements Command{
    @Override
    public String execute(List<String> list) {
        Array<Number> array = ParseArray.parseListToArray(list,list.remove(list.size()-1));
        String result = LocaleSingleton.getResourceBundle().getString("BEFORE")
                + '\n' + array.getInfo() + '\n';
        new MergeSorting().sort(array);
        result += '\n' + LocaleSingleton.getResourceBundle().getString("AFTER")
                + '\n' + array.getInfo();
        return result;
    }
}
