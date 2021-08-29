package by.parakhnevich.oop.service.print;

import by.parakhnevich.oop.bean.voucher.Voucher;

import java.util.List;
import java.util.Locale;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * Sort list of Voucher via country
 * @autor Danila Parakhnevich
 * @version 1.1
 * @see by.parakhnevich.oop.service.print.SortVoucherWithChoise
 */
public class SortViaCountry implements SortVoucherWithChoise{
    public void sort(List<Voucher> list, String obj) {
        BiConsumer<List<Voucher>, String> func = (List<Voucher> listToSort, String name) -> {
            for (int i = 0; i < listToSort.size(); ++i) {
                if (!listToSort.get(i).getCountry().toLowerCase(Locale.ROOT).equals(
                        (name.toLowerCase(Locale.ROOT)))) {
                     listToSort.remove(i--);
                }
            }
        };
        func.accept(list, obj);
    }
}
