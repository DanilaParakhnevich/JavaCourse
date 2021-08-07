package by.parakhnevich.branchingandloops.controller.command;

import by.parakhnevich.branchingandloops.utilloops.comparator.TwoNumbersComparator;
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
public class IsOneNumberEqualsAnotherCommand  implements Command{
    @Override
    public String execute(List<String> list) {
        TwoNumbersComparator twoNumbersComparator = new TwoNumbersComparator(Double.parseDouble(list.get(0))
                ,Double.parseDouble(list.get(1)));
        return String.format(LocaleSingleton.getResourceBundle().getString("RESULT_5"),
                twoNumbersComparator.compare());
    }
}
