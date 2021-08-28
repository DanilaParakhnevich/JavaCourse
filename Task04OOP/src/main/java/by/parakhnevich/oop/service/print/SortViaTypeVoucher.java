package by.parakhnevich.oop.service.print;

import by.parakhnevich.oop.bean.voucher.Voucher;

import java.util.List;
import java.util.Locale;

/**
 * Sort list of Voucher via type of voucher
 * @autor Danila Parakhnevich
 * @version 1.1
 * @see by.parakhnevich.oop.service.print.SortVoucherWithChoise
 */
public class SortViaTypeVoucher implements SortVoucherWithChoise{
    @Override
    public void sort(List<Voucher> list, String obj) {
        for (int i = 0; i < list.size(); ++i){
            if (list.get(i).getTypeOfVoucher().toLowerCase(Locale.ROOT).
                    compareTo(obj.toLowerCase(Locale.ROOT)) != 0){
                list.remove(i);
            }
        }
    }
}
