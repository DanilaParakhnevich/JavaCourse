package by.parakhnevich.oop.controller.command;

import by.parakhnevich.oop.bean.voucher.Voucher;

import java.io.IOException;
import java.util.List;

public interface Command {
    public String execute(List<Voucher> list) throws IOException;
}
