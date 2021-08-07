package by.parakhnevich.branchingandloops.controller.command;

import by.parakhnevich.branchingandloops.bean.Triangle;
import by.parakhnevich.branchingandloops.controller.Parser;
import by.parakhnevich.branchingandloops.utilloops.TriangleDefinator;
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
public class CheckingRectangularCommand implements Command{
    @Override
    public String execute(List<String> list) {
        Triangle triangle = new Triangle(Parser.parseListToDouble(list));
        TriangleDefinator triangleDefinator = new TriangleDefinator(triangle);

        String result = "";
        result += String.format(LocaleSingleton.getResourceBundle().getString("RESULT_3_1"),
                triangleDefinator.isTriangle());
        if (triangleDefinator.isTriangle()){
            result += '\n' + String.format(LocaleSingleton.getResourceBundle().getString("RESULT_3_2"),
                    triangleDefinator.isRectangular());
        }
        return result;
    }
}
