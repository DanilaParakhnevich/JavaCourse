package by.parakhnevich.branchingandloops.controller.command;

import by.parakhnevich.branchingandloops.controller.Parser;
import by.parakhnevich.branchingandloops.utilloops.FunctionCalculator;
import by.parakhnevich.branchingandloops.view.locale.LocaleSingleton;

import java.util.List;

/**
 * Class {@code Object} implements Command and accept info from View across
 * Controller and return necessary info
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.branchingandloops.view.View
 * @see by.parakhnevich.branchingandloops.controller.Controller
 * @see by.parakhnevich.branchingandloops.controller.command.Command
 */
public class CalculateFunctionCommand implements Command{
    @Override
    public String execute(List<String> list) {
        FunctionCalculator functionCalculator = new FunctionCalculator(Parser.parseListToDouble(list));
        functionCalculator.printFunction();
        return String.format(LocaleSingleton.getResourceBundle().getString("RESULT_2"),
                functionCalculator.calculate());
    }
}
