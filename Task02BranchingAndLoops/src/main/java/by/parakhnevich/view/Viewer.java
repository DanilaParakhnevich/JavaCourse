package by.parakhnevich.view;

import by.parakhnevich.controller.Controller;
import by.parakhnevich.reader.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Viewer {
    private Scanner scanForListener;
    private Scanner scan;
    private boolean isFromFile;

    private static final List<Reader> listOfReaders = new ArrayList<>();

    static final String CHOOSE_FILE = "Enter *path*/*name of file + .txt*";
    static final String CHOOSE_METHOD = "Choose method of reading information for 1 task" +
            '\n' + "Enter 1 to choose method reading from console" +
            '\n' + "Enter 2 to choose method reading from file";
    static final String INTRODUCTION = "Choose number of task entering number (1-10)" +
            '\n' + "Or enter 0 to end program";
    static final String TRY_AGAIN = "Try again . You have entered invalid number";

    public Viewer(){
        scanForListener = new Scanner(System.in);

        System.out.println(CHOOSE_METHOD);

        int number = scanForListener.nextInt();

        while (number != 1 && number != 2) {
            System.out.println(TRY_AGAIN);
            number = scanForListener.nextInt();
        }
        scan = createScanner(number);

        listOfReaders.add(new ReadThreeNumbers());
        listOfReaders.add(new ReadTwoNumbers());
        listOfReaders.add(new ReadThreePoints());
        listOfReaders.add(new ReadOfTwoAngles());
        listOfReaders.add(new ReadXValue());
    }

    private Scanner createScanner(int value){
        if (value == 1){
            isFromFile = false;
            return new Scanner(System.in);
        }
        System.out.println(CHOOSE_FILE);
        isFromFile = true;
        return new Scanner(scanForListener.nextLine());
    }

    public void view(){
        System.out.println(INTRODUCTION);
        int number = Integer.parseInt(scan.nextLine());
        System.out.println(number);

        while (number < 0 || number > 10) {
            System.out.println(TRY_AGAIN);
            number = Integer.parseInt(scan.nextLine());
        }

        if (number != 0) {
            try {
                Controller controller = new Controller(number ,
                        listOfReaders.get(number-1).read(scanForListener,isFromFile));
                controller.doRequest();
                view();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
