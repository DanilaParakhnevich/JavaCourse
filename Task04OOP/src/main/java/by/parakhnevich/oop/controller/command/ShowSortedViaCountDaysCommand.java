package by.parakhnevich.oop.controller.command;

import by.parakhnevich.oop.bean.voucher.Voucher;
import by.parakhnevich.oop.service.print.ListVoucherToString;
import by.parakhnevich.oop.service.print.SortViaCountOfDays;

import java.util.List;

public class ShowSortedViaCountDaysCommand implements Command{
    @Override
    public String execute(List<Voucher> list) {
        new SortViaCountOfDays().sort(list);
        return new ListVoucherToString().get(list);
    }
}
