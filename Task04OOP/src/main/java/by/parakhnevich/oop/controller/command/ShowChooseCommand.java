package by.parakhnevich.oop.controller.command;

import by.parakhnevich.oop.bean.voucher.Voucher;
import by.parakhnevich.oop.dao.VoucherDAO;
import by.parakhnevich.oop.view.locale.LocaleSingleton;

import java.io.IOException;
import java.util.List;

/**
 * Implementer of Command
 * @autor Danila Parakhnevich
 * @version 1.1
 * @see by.parakhnevich.oop.controller.command.Command
 */
public class ShowChooseCommand implements Command{
    @Override
    public String execute(List<String> list) throws IOException {
        List<Voucher> listOfVouchers =
                new VoucherDAO().getAll(2);
        int choise = Integer.parseInt(list.get(1));
        if (choise > listOfVouchers.size() || choise < 1) {
            return LocaleSingleton.getResourceBundle().getString("TRY_AGAIN");
        }
        Voucher voucher = listOfVouchers.get(choise - 1);
        return voucher.get();
    }
}
