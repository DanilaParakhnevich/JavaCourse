package by.parakhnevich.branchingandloops.controller.command;

import by.parakhnevich.branchingandloops.utilbranches.PrintNumbers;

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
public class PrintNumbersCommand implements Command{

    @Override
    public String execute(List<String> list) {
        PrintNumbers printNumbers = new PrintNumbers(2,100);
        return printNumbers.subs();
    }
}
