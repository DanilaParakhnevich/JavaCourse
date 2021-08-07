package by.parakhnevich.branchingandloops.reader;

import by.parakhnevich.branchingandloops.reader.exception.TooMuchArgumentsException;
import by.parakhnevich.branchingandloops.view.locale.LocaleSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @autor Danila Parakhnevich
 * @version 1.0
 */
public class ReadThreePoints implements Reader{
    private static final Logger logger = LogManager.getRootLogger();



    @Override
    public List<String> read(Scanner scan, boolean isFromFile) throws TooMuchArgumentsException {
        List<String> list = new ArrayList<>();
        System.out.println(LocaleSingleton.getResourceBundle().getString("INTRODUCTION_5"));
        try {
            for (int index = 0; index < 3; index++) {
                System.out.println(LocaleSingleton.getResourceBundle().getString("ENTER_X"));
                list.add(scan.next());
                System.out.println(LocaleSingleton.getResourceBundle().getString("ENTER_Y"));
                list.add(scan.next());
            }
            if (isFromFile && scan.hasNext()) {
                throw new TooMuchArgumentsException(3);
            }
        } catch (TooMuchArgumentsException e) {
            logger.error(e);
        }
        return list;
    }
}
