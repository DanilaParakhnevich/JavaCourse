package by.parakhnevich.branchingandloops.reader;

import by.parakhnevich.branchingandloops.reader.exception.ReadNException;
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
public class ReadNValue implements Reader{
    private static final Logger logger = LogManager.getRootLogger();

    @Override
    public List<String> read(Scanner scan, boolean isFromFile) throws TooMuchArgumentsException {
        List<String> list = new ArrayList<>();
        System.out.println(LocaleSingleton.getResourceBundle().getString("INTRODUCTION_2"));
        try {
            list.add(scan.next());
            if (Double.parseDouble(list.get(0)) < 1){
                throw new ReadNException("N - value must be >= 1");
            }
            if (isFromFile && scan.hasNext()) {
                throw new TooMuchArgumentsException(1);
            }
        } catch (TooMuchArgumentsException | ReadNException e) {
            logger.error(e);
        }
        return list;
    }
}
