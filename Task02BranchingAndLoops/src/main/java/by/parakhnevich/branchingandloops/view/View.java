package by.parakhnevich.branchingandloops.view;

import by.parakhnevich.branchingandloops.controller.Controller;
import by.parakhnevich.branchingandloops.reader.*;
import by.parakhnevich.branchingandloops.view.locale.LocaleSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;


/**
 * Class {@code Object} view for viewer information and
 * requests him for feedback and requests
 * this feedback to Controller
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.branchingandloops.controller.Controller
 */
public class View {
    private final Scanner scanForListener;
    private boolean isFromFile;
    private final Logger logger = LogManager.getLogger(View.class.getName());
    private static final List<Reader> listOfReaders = new ArrayList<>();

    //Constructor init scanner for primary communication with viewer
    //and init readers for all the tasks
    public View(){
        scanForListener = new Scanner(System.in);

        listOfReaders.add(new ReadThreeNumbers());
        listOfReaders.add(new ReadTwoNumbers());
        listOfReaders.add(new ReadThreePoints());
        listOfReaders.add(new ReadOfTwoAngles());
        listOfReaders.add(new ReadXValue());
        listOfReaders.add(new ReadEpsilon());
        listOfReaders.add(new ReadIgnore());
        listOfReaders.add(new ReadTwoNumbers());
        listOfReaders.add(new ReadNValue());
        listOfReaders.add(new ReadOneNumber());
    }

    public void view(){
        int number = printNumberOfTask();
        if (number != 0) {
            try {
                Controller controller = new Controller(number);
                Scanner scan = null;
                if (number != 7) {
                    scan = createScanner();
                }
                System.out.println(controller.doRequest(listOfReaders.get(number - 1).read(scan,
                        isFromFile)));
                logger.info("Request has done");
                new View().view();
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }

    private Scanner createScanner(){
        System.out.println(LocaleSingleton.getResourceBundle().getString("VIEW_1") +
                '\n' + LocaleSingleton.getResourceBundle().getString("VIEW_2") +
                '\n' + LocaleSingleton.getResourceBundle().getString("VIEW_3"));
        int number = scanForListener.nextInt();
        while (number != 1 && number != 2) {
            System.out.println(LocaleSingleton.getResourceBundle().getString("TRY_AGAIN"));
            number = scanForListener.nextInt();
        }
        if (number == 1){
            isFromFile = false;
            return new Scanner(System.in);
        }
        System.out.println(LocaleSingleton.getResourceBundle().getString("CHOOSE_FILE"));
        isFromFile = true;
        return new Scanner(scanForListener.nextLine());
    }

    private int printNumberOfTask(){
        System.out.println(LocaleSingleton.getResourceBundle().getString("INTRODUCTION"));
        scanForListener.reset();
        int number = Integer.parseInt(scanForListener.next());
        System.out.println(number);
        while (number < 0 || number > 10) {
            System.out.println(LocaleSingleton.getResourceBundle().getString("TRY_AGAIN"));
            number = Integer.parseInt(scanForListener.nextLine());
        }
        return number;
    }

    public LocaleSingleton createLocale(){
        System.out.println("Select language :" +
                '\n' + "Enter 1 to select english" +
                '\n' + "Enter 2 to select russian");
        int number = Integer.parseInt(scanForListener.next());
        while (number != 1 && number != 2) {
            System.out.println("Try again.");
            number = Integer.parseInt(scanForListener.next());
        }

        return number == 1?LocaleSingleton.getInstance("en","EN") :
                LocaleSingleton.getInstance("ru","RU");
    }
}
