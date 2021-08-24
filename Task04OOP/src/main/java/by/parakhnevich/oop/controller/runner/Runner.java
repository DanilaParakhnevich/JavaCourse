package by.parakhnevich.oop.controller.runner;

import by.parakhnevich.oop.bean.voucher.Voucher;
import by.parakhnevich.oop.dao.VoucherDAO;
import by.parakhnevich.oop.service.print.ListVoucherToString;

import java.io.IOException;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws IOException {
           /* View view = new View();
            view.createLocale();
            view.execute();*/
        List<Voucher> list = new VoucherDAO().getAll();
        System.out.println(new ListVoucherToString().get(list));
    }
}
