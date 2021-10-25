package by.parakhnevich.task08xmlparsing.view;

import by.parakhnevich.task08xmlparsing.controller.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {
    static final String CHOOSE_FILE = "Enter name of file";
    static final String CHOOSE_METHOD = "Choose method of parsing : \n1.DOM Parser\n2.SAX Parser\n2.StAX Parser";
    static final String TRY_AGAIN = "Not corrected value, try again";
    static final String DOM = "CONSOLE_PRINT_DOM";
    static final String SAX = "CONSOLE_PRINT_SAX";
    static final String STAX = "CONSOLE_PRINT_STAX";
    static final String EXIT_OR_NOT = "Do u want to exit program?\n1.Yes\n2.No";
    Scanner scan = new Scanner(System.in);
    Controller controller = new Controller();
    Logger logger = (Logger) LogManager.getLogger(View.class);

    public void execute() {
        while (true) {
            List<String> list = new ArrayList<>();
            choosingMethod(list);
            selectingFile(list);
            try {
                System.out.println(controller.execute(list));
            } catch (IOException e) {
                logger.error(e);
            }
            if (exitOrNot()) {
                return;
            }
        }
    }
    private boolean exitOrNot(){
        System.out.println(EXIT_OR_NOT);
        int method = scan.nextInt();
        while (method != 1 && method != 2) {
            System.out.println(TRY_AGAIN);
            method = scan.nextInt();
        }
        return method == 1;
    }

    private void choosingMethod(List<String> list) {
        System.out.println(CHOOSE_METHOD);
        int method = scan.nextInt();
        while (method < 1 || method > 3) {
            System.out.println(TRY_AGAIN);
            method = scan.nextInt();
        }
        switch (method) {
            case 1:
                list.add(DOM);
                break;
            case 2:
                list.add(SAX);
                break;
            default:
                list.add(STAX);
                break;
        }
    }

    public void selectingFile(List<String> list) {
        System.out.println(CHOOSE_FILE);
        String file = scan.next();
        list.add(file);
    }
}
