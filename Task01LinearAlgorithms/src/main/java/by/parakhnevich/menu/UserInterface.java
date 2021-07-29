package by.parakhnevich.menu;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*
 * This user interface allows you ask the user
 * to choose number of task [1 ; 5] and to
 * choose a method [1 ; 2]
 */

public class UserInterface {
    private static final String HUB = "Enter number of task (1-5) or 0 to close program";
    private static final String CHOOSING_OF_METHOD = "Enter 1 to choose method loading information from file or \n" +
            "2 to choose method loading information from console";
    private static final String TRY_AGAIN = "You entered not correct number . Try again";
    private final Scanner scan;
    private int numberOfMethod;

    public UserInterface() {
        numberOfMethod = 0;
        scan = new Scanner(System.in);
    }

    public int chooseNumberOfTask(){
        System.out.println(HUB);
        int number = scan.nextInt();
        while (number < 0 || number > 5){
            System.out.println(TRY_AGAIN);
            number = scan.nextInt();
        }
        return number;
    }

    public Scanner chooseAMethod() throws IOException {
        System.out.println(CHOOSING_OF_METHOD);
        int number = scan.nextInt();
        while (number != 1 && number != 2) {
            System.out.println(TRY_AGAIN);
            number = scan.nextInt();
        }
        numberOfMethod = number;
        return number == 1 ? new Scanner(new File(new File("text.txt").getAbsolutePath())) : new Scanner(System.in);
    }

    public int getNumberOfMethod() {
        return numberOfMethod;
    }
}
