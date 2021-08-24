package by.parakhnevich.oop.service.print;

import by.parakhnevich.oop.bean.voucher.Voucher;

import java.util.List;

public class GetVoucher {
    public String get(int number, List<Voucher> list){
        if (number < 0 || number > list.size()) {
            return null;
        }
        return list.get(number).get();// TODO: 24.08.2021
    }
}
