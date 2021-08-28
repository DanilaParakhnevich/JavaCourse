package by.parakhnevich.oop.controller.command;

import by.parakhnevich.oop.bean.voucher.Voucher;
import by.parakhnevich.oop.dao.VoucherDAO;
import by.parakhnevich.oop.service.print.ListVoucherToString;

import java.io.IOException;
import java.util.List;

/**
 * Implementer of Command
 * @autor Danila Parakhnevich
 * @version 1.1
 * @see by.parakhnevich.oop.controller.command.Command
 */
public class ShowListCommand implements Command{
    @Override
    public java.lang.String execute(List<String> list) throws IOException {
        List<Voucher> listOfVouchers =
                new VoucherDAO().getAll(2);
        return new ListVoucherToString().get(listOfVouchers);
    }
}
