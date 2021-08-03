package by.parakhnevich.exception;

public class TooMuchArguments extends Exception{
    public TooMuchArguments(int number) {
        super("There are much than " + number + "arguments");
    }
}
