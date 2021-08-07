package by.parakhnevich.branchingandloops.reader.exception;


/**
 * @autor Danila Parakhnevich
 * @version 1.0
 */
public class TooMuchArgumentsException extends Exception{
    public TooMuchArgumentsException(int number) {
        super("There are much than " + number + "arguments");
    }
}
