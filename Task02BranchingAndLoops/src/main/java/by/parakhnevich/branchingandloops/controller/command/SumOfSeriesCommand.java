package by.parakhnevich.branchingandloops.controller.command;

import by.parakhnevich.branchingandloops.utilbranches.SumOfSeries;
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
public class SumOfSeriesCommand implements Command {
    @Override
    public String execute(List<String> list) {
        SumOfSeries sumOfSeries = new SumOfSeries(Double.parseDouble(list.get(0)));
        return (sumOfSeries.showInfoAboutSeries() +
                '\n' + String.format(LocaleSingleton.getResourceBundle().getString("RESULT_8"),
                sumOfSeries.calculate()));
    }
}
