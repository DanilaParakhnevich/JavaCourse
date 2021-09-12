package by.parakhnevich.multithreadingmatrix.view;

import by.parakhnevich.multithreadingmatrix.controller.Controller;
import by.parakhnevich.multithreadingmatrix.view.locale.LocaleSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {
    private static final Controller controller = new Controller();
    private static final String CHOOSE_LANGUAGE = "Choose language " +
            "\n1.English \n2.Russian";
    private static final String TRY_AGAIN = "TRY_AGAIN";
    Scanner scanForListener = new Scanner(System.in);


    public void execute(){
        for (int i = 0; i < 5; i++) {
            System.out.println(i + 1 + " " + LocaleSingleton.
                    getInstance().getResourceBundle().getString("VARIANT") + '\n');
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(i));
            System.out.println(controller.execute(list));
        }
    }

    public void createLocale() {
        System.out.println(CHOOSE_LANGUAGE);
        int number = Integer.parseInt(scanForListener.next());
        while (number != 1 && number != 2) {
            System.out.println(TRY_AGAIN);
            number = Integer.parseInt(scanForListener.next());
        }

        if (number == 1) {
            LocaleSingleton.getInstance().createLocale("en", "EN");
        } else {
            LocaleSingleton.getInstance().createLocale("ru", "RU");
        }
    }
}
