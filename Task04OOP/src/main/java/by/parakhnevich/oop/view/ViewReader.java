package by.parakhnevich.oop.view;

import by.parakhnevich.oop.controller.Controller;
import by.parakhnevich.oop.view.locale.LocaleSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class - helper for class View
 * @autor Danila Parakhnevich
 * @version 1.1
 * @see by.parakhnevich.oop.view.View
 */
public class ViewReader {
    Scanner scan = new Scanner(System.in);
    private static final String CHOOSE_LANGUAGE = "Choose language " +
            "\n1.English \n2.Russian";
    private static final String TRY_AGAIN = "TRY_AGAIN";
    Controller controller = new Controller();

    public int menu() {
        System.out.println(LocaleSingleton.getResourceBundle().getString("MENU"));
        int number = Integer.parseInt(scan.next());
        while (number < 0 || number > 6) {
            System.out.println(LocaleSingleton.getResourceBundle().getString(TRY_AGAIN));
            number = Integer.parseInt(scan.next());
        }
        return number;
    }

    public String enterSortedElement(String number) {
        if (number.equals("4")) {
            System.out.println(LocaleSingleton.getResourceBundle().getString("COUNTRY"));
        }
        else {
            System.out.println(LocaleSingleton.getResourceBundle().getString("TYPE"));
        }
        return scan.next();
    }

    public String afterViewList(){
        System.out.println(LocaleSingleton.getResourceBundle().getString("AFTER_PRINT"));
        String result = scan.next();
        while (!result.equals("1") && !result.equals("2")){
            System.out.println(LocaleSingleton.getResourceBundle().getString(TRY_AGAIN));
            result = scan.next();
        }
        return result;
    }

    public String pickVoucher(){
        System.out.println(LocaleSingleton.getResourceBundle().getString("PICK_VOUCHER"));
        return scan.next();
    }

    public int chooseTransport(String number) {
        List<String> listToController = new ArrayList<>();
        listToController.add("9");
        listToController.add(number);
        int size = Integer.parseInt(controller.doRequest(listToController));
        listToController = new ArrayList<>();
        listToController.add("10");
        listToController.add(number);
        if (size != 1) {
            System.out.println(LocaleSingleton.getResourceBundle().getString("CHOOSE_TRANSPORT"));
            return showSomething(listToController, size);
        }
        return 1;
    }

    private int showSomething(List<String> listToController, int size) {
        System.out.println(controller.doRequest(listToController));
        int choose = Integer.parseInt(scan.next());
        while (choose < 0 || choose > size){
            System.out.println(LocaleSingleton.getResourceBundle().getString(TRY_AGAIN));
            choose = Integer.parseInt(scan.next());
        }
        return choose;
    }

    public int chooseFood(String number) {
        List<String> listToController = new ArrayList<>();
        listToController.add("11");
        listToController.add(number);
        int size = Integer.parseInt(controller.doRequest(listToController));
        listToController = new ArrayList<>();
        listToController.add("12");
        listToController.add(number);
        System.out.println(LocaleSingleton.getResourceBundle().getString("CHOOSE_FOOD"));
        return showSomething(listToController, size);
    }

    public int chooseCountOfDays(String number) {
        System.out.println(LocaleSingleton.getResourceBundle().getString("CHOOSE_COUNT_OF_DAYS"));
        List<String> listToController = new ArrayList<>();
        listToController.add("8");
        listToController.add(number);
        int maxDays = Integer.parseInt(new Controller().
                doRequest(listToController));
        int days = Integer.parseInt(scan.next());
        while (days > maxDays || days < 1){
            System.out.println(LocaleSingleton.getResourceBundle().getString(TRY_AGAIN));
            days = Integer.parseInt(scan.next());
        }
        return days;
    }

    //use singleton class and create localisation
    public void createLocale() {
        System.out.println(CHOOSE_LANGUAGE);
        int number = Integer.parseInt(scan.next());
        while (number != 1 && number != 2) {
            System.out.println(TRY_AGAIN);
            number = Integer.parseInt(scan.next());
        }

        if (number == 1) {
            LocaleSingleton.getInstance("en", "EN");
        } else {
            LocaleSingleton.getInstance("ru", "RU");
        }
    }

    public String isLeave() {
        System.out.println(LocaleSingleton.getResourceBundle().getString("IS_LEAVE"));
        return scan.next();
    }
}
