package by.parakhnevich.oop.view;

import by.parakhnevich.oop.bean.voucher.Voucher;
import by.parakhnevich.oop.controller.command.Command;
import by.parakhnevich.oop.controller.command.ShowListCommand;
import by.parakhnevich.oop.dao.VoucherDAO;
import by.parakhnevich.oop.view.locale.LocaleSingleton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {
    Scanner scan = new Scanner(System.in);
    List<Command> list = new ArrayList<>();
    List<Voucher> listOfVoucher = new VoucherDAO().getAll();

    private static final String CHOOSE_LANGUAGE = "Choose language " +
            "\n1.English \n2.Russian";
    private static final String TRY_AGAIN = "TRY_AGAIN";

    public View() throws IOException{

        list.add(new ShowListCommand());
    }

    //use singleton class and create localisation
    public void createLocale() {
        System.out.println(CHOOSE_LANGUAGE);
        int number = Integer.parseInt(scan.next());
        while (number != 1 && number != 2) {
            System.out.println("Try again.");
            number = Integer.parseInt(scan.next());
        }

        if (number == 1) {
            LocaleSingleton.getInstance("en", "EN");
        } else {
            LocaleSingleton.getInstance("ru", "RU");
        }
    }
}
