package by.parakhnevich.oop.view;

import by.parakhnevich.oop.controller.Controller;
import by.parakhnevich.oop.view.locale.LocaleSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class that used in Main Class
 * It contains all the necessary methods to work with fourth
 * task
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.oop.controller.runner.Runner
 */
public class View {
    ViewReader reader = new ViewReader();
    Controller controller = new Controller();

    public void execute() {
        while(true) {
            String firstShow = show();
            if (firstShow.equals("")) {
                System.out.println(LocaleSingleton.getResourceBundle().getString("LIST_CLEAR"));
                execute();
                return;
            }
            System.out.println(firstShow);
            String afterPrint = reader.afterViewList();
            if (afterPrint.equals("2")) {
                execute();
                return;
            }
            System.out.println(chooseVoucherShow());
            System.out.println(editAndShowVoucher());

            if (!Objects.equals(reader.isLeave(), "1")) {
                return;
            }
        }

    }
    private String show(){
        List<String> listToController = new ArrayList<>();
        listToController.add(String.valueOf(reader.menu()));
        if (listToController.get(0).equals("0")) {
            System.exit(0);
        }
        if (listToController.get(0).equals("4") || listToController.get(0).equals("5")) {
            listToController.add(reader.
                    enterSortedElement(listToController.get(0)));
        }
        return controller.doRequest(listToController);
    }

    String numberOfChooseVoucher;

    private String chooseVoucherShow() {
        List<String> listToController = new ArrayList<>();
        listToController.add("7");
        listToController.add(reader.pickVoucher());
        String chooseVoucher = controller.doRequest(listToController);
        while (chooseVoucher.equals(
                LocaleSingleton.getResourceBundle().getString("TRY_AGAIN"))) {
            System.out.println(chooseVoucher);
            listToController.remove(1);
            listToController.add(reader.pickVoucher());
            chooseVoucher = controller.doRequest(listToController);
        }
        numberOfChooseVoucher = listToController.get(1);
        return chooseVoucher;
    }

    private String editAndShowVoucher(){
        List<String> listToController = new ArrayList<>();
        listToController.add("13");
        int food = reader.chooseFood(numberOfChooseVoucher);
        int transport = reader.chooseTransport(numberOfChooseVoucher);
        int count = reader.chooseCountOfDays(numberOfChooseVoucher);
        listToController.add(numberOfChooseVoucher);
        listToController.add(String.valueOf(transport));
        listToController.add(String.valueOf(food));
        listToController.add(String.valueOf(count));
        return controller.doRequest(listToController);
    }
}
