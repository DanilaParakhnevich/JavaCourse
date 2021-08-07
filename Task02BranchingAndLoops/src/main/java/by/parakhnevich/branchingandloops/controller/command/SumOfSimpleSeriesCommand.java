package by.parakhnevich.branchingandloops.controller.command;

import by.parakhnevich.branchingandloops.utilbranches.SumOfSimpleSeries;
import by.parakhnevich.branchingandloops.view.locale.LocaleSingleton;

import java.util.List;
import java.util.Locale;


/**
 * Class {@code Object} implements Command and accept info from View across
 * Controller and return necessary info
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.branchingandloops.view.View
 * @see by.parakhnevich.branchingandloops.controller.Controller
 * @see by.parakhnevich.branchingandloops.controller.command.Command
 */
public class SumOfSimpleSeriesCommand implements Command{
    @Override
    public String execute(List<String> list) {
        SumOfSimpleSeries sumOfSimpleSeries = new SumOfSimpleSeries(Integer.parseInt(list.get(0)));
        return LocaleSingleton.getResourceBundle().getString("INFO_9")
                + '\n' + String.format(LocaleSingleton.getResourceBundle().getString("RESULT_9"),
                sumOfSimpleSeries.calculate());
    }
}
