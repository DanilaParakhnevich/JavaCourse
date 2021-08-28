package by.parakhnevich.oop.service.print;

import by.parakhnevich.oop.bean.voucher.Voucher;

import java.util.List;

/**
 * Class return string from list of vouchers for task 04
 * @autor Danila Parakhnevich
 * @version 1.1
 */
public class ListVoucherToString {
    public String get(List<Voucher> list){
        StringBuilder result = new StringBuilder();
        int counter = 1;
        for (Voucher element : list) {
            result.append(counter).append(". ").append(element.get()).append('\n');
            ++counter;
        }
        return result.toString();
    }
}
