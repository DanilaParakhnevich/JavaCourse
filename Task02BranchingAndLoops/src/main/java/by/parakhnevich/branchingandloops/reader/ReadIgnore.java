package by.parakhnevich.branchingandloops.reader;

import by.parakhnevich.branchingandloops.reader.exception.TooMuchArgumentsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @autor Danila Parakhnevich
 * @version 1.0
 */
public class ReadIgnore implements Reader{
    public List<String> read(Scanner scan, boolean isFromFile) throws TooMuchArgumentsException {
        return new ArrayList<>();
    }
}
