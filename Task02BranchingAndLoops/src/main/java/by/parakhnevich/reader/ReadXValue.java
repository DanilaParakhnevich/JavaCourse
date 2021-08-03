package by.parakhnevich.reader;

import by.parakhnevich.exception.TooMuchArguments;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadXValue implements Reader{
    private static final String INTRODUCTION = "Please enter x - value";


    @Override
    public List<Number> read(Scanner scan, boolean isFromFile) throws TooMuchArguments {
        List<Number> list = new ArrayList<>();

        System.out.println(INTRODUCTION);

        try {
            list.add(Double.parseDouble(scan.next()));
            if (isFromFile && scan.hasNext()) {
                throw new TooMuchArguments(1);
            }
        } catch (TooMuchArguments e) {
            e.printStackTrace();
        }
        return list;
    }
}
