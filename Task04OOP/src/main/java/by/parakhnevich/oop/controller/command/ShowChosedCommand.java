package by.parakhnevich.oop.controller.command;

import by.parakhnevich.oop.bean.voucher.Voucher;

import java.util.List;

public class ShowChosedCommand implements Command{
    @Override
    public String execute(List<Voucher> list) {
        return "" + list.get(0);
    }
}
