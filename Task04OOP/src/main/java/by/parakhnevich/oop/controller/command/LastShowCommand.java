package by.parakhnevich.oop.controller.command;

import by.parakhnevich.oop.bean.voucher.Voucher;
import by.parakhnevich.oop.dao.VoucherDAO;
import by.parakhnevich.oop.service.print.PrintFullVoucher;

import java.io.IOException;
import java.util.List;

/**
 * Implementer of Command
 * @autor Danila Parakhnevich
 * @version 1.1
 * @see by.parakhnevich.oop.controller.command.Command
 */
public class LastShowCommand implements Command{
    @Override
    public String execute(List<String> list) throws IOException {
        Voucher voucher = new VoucherDAO().getAll(2).
                get(Integer.parseInt(list.get(1)) - 1);
        voucher.setTypeOfTransport(voucher.getListOfAcceptedTransports().get(
                Integer.parseInt(list.get(2))));
        voucher.setTypeOfFood(voucher.getListOfAcceptedFood().get(
                Integer.parseInt(list.get(3))));
        voucher.setCountOfDays(Integer.parseInt(list.get(4)));
        return new PrintFullVoucher().get(voucher);
    }
}
