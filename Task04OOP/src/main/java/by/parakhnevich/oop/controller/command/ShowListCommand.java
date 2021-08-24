package by.parakhnevich.oop.controller.command;

import by.parakhnevich.oop.bean.voucher.Voucher;
import by.parakhnevich.oop.service.print.ListVoucherToString;

import java.util.List;

public class ShowListCommand implements Command{
    @Override
    public String execute(List<Voucher> list) {
        return new ListVoucherToString().get(list);
    }
}
