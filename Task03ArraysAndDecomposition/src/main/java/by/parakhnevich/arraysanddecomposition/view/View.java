package by.parakhnevich.arraysanddecomposition.view;


import by.parakhnevich.arraysanddecomposition.controller.Controller;
import by.parakhnevich.arraysanddecomposition.reader.*;
import by.parakhnevich.arraysanddecomposition.view.locale.LocaleSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class that used in Main Class
 * It contains all the necessary methods to work with third
 * task
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.controller.runner.Runner
 */
public class View {
    Scanner scanForListener = new Scanner(System.in);
    Scanner scanner;
    private final boolean[] flags = new boolean[3];
    private final Logger logger = LogManager.getLogger(View.class.getName());
    private static final List<Reader> listOfReaders = new ArrayList<>();

    private static final String CHOOSE_LANGUAGE = "Choose language " +
            "\n1.English \n2.Russian";
    private static final String TRY_AGAIN = "TRY_AGAIN";

    public View() {
        listOfReaders.add(new ReadArray());
        listOfReaders.add(new ReadMatrix());
        listOfReaders.add(new ReadTwoMatrices());
        listOfReaders.add(new ReadNameOfFile());
    }
    //method that used in runner and plays the main role
    public void view() throws Exception {
        int number1 = printBean();
        if (number1 == 0) {
            return;
        }
        int number2 = printNumberOfTask(number1);
        if (number2 == 0) {
            return;
        }
        else if (number2 != 7) {
            scanner = createScanner();
        }
        else {
            scanner = scanForListener;
        }
        int index;
        if (number1 == 1 && number2 != 7) {
            index = 0;
        }
        else if(number1 == 2 && number2 > 3) {
            index = 1;
        }
        else if (number2 == 7) {
            index = 3;
        }
        else {
            index = 2;
        }
        String numberOfType;
        numberOfType = printType();
        System.out.println(new Controller(number2, number1, numberOfType)
                .doRequest(listOfReaders.get(index).read(scanner, flags)));
        view();
    }
    //method that requests type
    private String printType(){
        Scanner scan = new Scanner(System.in);
        int number;
        System.out.println(LocaleSingleton.getResourceBundle().getString("PICK_TYPE"));
        number = Integer.parseInt(scanForListener.next());
        while (number > 6 || number < 1){
            System.out.println(LocaleSingleton.getResourceBundle().getString(TRY_AGAIN));
            number = Integer.parseInt(scanForListener.next());
        }
        switch (number){
            case 1 : return "int";
            case 2 : return "double";
            case 3 : return "short";
            case 4 : return "long";
            case 5 : return "float";
            default : return "byte";
        }
    }
    //method that requests bean (array or matrix)
    private int printBean(){
        System.out.println(LocaleSingleton.getResourceBundle().getString("INTRODUCTION_1"));
        int number = Integer.parseInt(scanForListener.next());
        while (number != 1 && number != 2 && number != 0){
            System.out.println(LocaleSingleton.getResourceBundle().getString(TRY_AGAIN));
            number = Integer.parseInt(scanForListener.next());
        }
        return number;
    }

    //method that requests number of type
    private int printNumberOfTask(int number1) {
        int number2;
        if(number1 == 1) {
            System.out.println(LocaleSingleton.getResourceBundle().getString("INTRODUCTION_ARRAY"));
            number2 = Integer.parseInt(scanForListener.next());
            while (number2 < 0 || number2 > 8) {
                System.out.println(LocaleSingleton.getResourceBundle().getString(TRY_AGAIN));
                number2 = Integer.parseInt(scanForListener.nextLine());
            }
        }
        else {
            System.out.println(LocaleSingleton.getResourceBundle().getString("INTRODUCTION_MATRIX"));
            number2 = Integer.parseInt(scanForListener.next());
            while (number2 < 0 || number2 > 5) {
                System.out.println(LocaleSingleton.getResourceBundle().getString(TRY_AGAIN));
                number2 = Integer.parseInt(scanForListener.nextLine());
            }
            flags[2] = number2 == 4;
        }
        return number2;
    }

    //method that requests how to create data for
    //working with third task
    private Scanner createScanner(){
        System.out.println(LocaleSingleton.getResourceBundle().getString("INTRODUCTION_2"));
        int number = scanForListener.nextInt();
        while (number != 1 && number != 2 && number != 3) {
            System.out.println(LocaleSingleton.getResourceBundle().getString(TRY_AGAIN));
            number = scanForListener.nextInt();
        }

        if (number == 1){
            flags[0] = true;
            flags[1] = false;
            System.out.println(LocaleSingleton.getResourceBundle().getString("CHOOSE_FILE"));
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("files/" + scanForListener.next()).getFile());
            String absolutePath = file.getAbsolutePath();
            file = new File(absolutePath);
            try {
                return new Scanner(file);
            } catch (FileNotFoundException e) {
                logger.error(e);
                logger.info("Chose random method");
            }
        }
        else if (number == 2){
            flags[0] = false;
            flags[1] = false;
            return scanForListener;
        }
        flags[0] = false;
        flags[1] = true;
        return scanForListener;
    }

    //use singleton class and create localisation
    public void createLocale() {
        System.out.println(CHOOSE_LANGUAGE);
        int number = Integer.parseInt(scanForListener.next());
        while (number != 1 && number != 2) {
            System.out.println("Try again.");
            number = Integer.parseInt(scanForListener.next());
        }

        if (number == 1) {
            LocaleSingleton.getInstance("en", "EN");
        } else {
            LocaleSingleton.getInstance("ru", "RU");
        }
    }

}

