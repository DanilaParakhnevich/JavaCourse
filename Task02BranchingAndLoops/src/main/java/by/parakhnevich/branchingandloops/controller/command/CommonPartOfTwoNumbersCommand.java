package by.parakhnevich.branchingandloops.controller.command;

import by.parakhnevich.branchingandloops.utilbranches.CommonPartOfTwoNumbers;
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
public class CommonPartOfTwoNumbersCommand implements Command{
    @Override
    public String execute(List<String> list) {
        CommonPartOfTwoNumbers commonPartOfTwoNumbers =
                new CommonPartOfTwoNumbers(Integer.parseInt(list.get(0)),Integer.parseInt(list.get(1)));
        return String.format(LocaleSingleton.getResourceBundle().getString("RESULT_4"),
                commonPartOfTwoNumbers.getFirst(), commonPartOfTwoNumbers.getSecond(),
                commonPartOfTwoNumbers.find());
    }
}
