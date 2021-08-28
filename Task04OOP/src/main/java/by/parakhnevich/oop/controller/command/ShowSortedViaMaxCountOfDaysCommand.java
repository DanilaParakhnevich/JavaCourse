package by.parakhnevich.oop.controller.command;

import by.parakhnevich.oop.bean.voucher.Voucher;
import by.parakhnevich.oop.dao.SaveChanges;
import by.parakhnevich.oop.dao.VoucherDAO;
import by.parakhnevich.oop.service.print.ListVoucherToString;
import by.parakhnevich.oop.service.print.SortViaMaxDays;

import java.io.IOException;
import java.util.List;

/**
 * Implementer of Command
 * @autor Danila Parakhnevich
 * @version 1.1
 * @see by.parakhnevich.oop.controller.command.Command
 */
public class ShowSortedViaMaxCountOfDaysCommand implements Command{
    @Override
    public String execute(List<String> list) throws IOException {
        List<Voucher> listOfVoucher = new VoucherDAO().getAll(2);
        new SortViaMaxDays().sort(listOfVoucher);
        new SaveChanges().save(listOfVoucher);
        return new ListVoucherToString().get(listOfVoucher);
    }
}
