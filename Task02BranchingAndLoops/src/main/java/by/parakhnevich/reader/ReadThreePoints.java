package by.parakhnevich.reader;

import by.parakhnevich.exception.TooMuchArguments;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadThreePoints implements Reader{
    private static final String INTRODUCTION = "Please enter 3 points";
    private static final String ENTER_X = "Enter x - value";
    private static final String ENTER_Y = "Enter y - value";



    @Override
    public List<Number> read(Scanner scan, boolean isFromFile) throws TooMuchArguments {
        List<Number> list = new ArrayList<>();

        System.out.println(INTRODUCTION);

        try {
            for (int index = 0; index < 3; index++) {
                System.out.println(ENTER_X);
                list.add(Double.parseDouble(scan.next()));
                System.out.println(ENTER_Y);
                list.add(Double.parseDouble(scan.next()));
            }

            if (isFromFile && scan.hasNext()) {
                throw new TooMuchArguments(3);
            }
        } catch (TooMuchArguments e) {
            e.printStackTrace();
        }
        return list;
    }
}
