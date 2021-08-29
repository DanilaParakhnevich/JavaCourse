package by.parakhnevich.oop.service.print;

import by.parakhnevich.oop.bean.voucher.Voucher;

import java.util.List;
import java.util.function.Consumer;

/**
 * Sort list of Voucher via cost
 * @autor Danila Parakhnevich
 * @version 1.1
 * @see by.parakhnevich.oop.service.print.SortVoucher
 */
public class SortViaCost implements SortVoucher {
    public void sort(List<Voucher> list) {
        Consumer<List<Voucher>> func = (List<Voucher> listToSort) -> {
            for (int i = 0; i < list.size(); ++i) {
                for (int j = 1; j < listToSort.size() - i; ++j) {
                if (listToSort.get(j - 1).getCostPerDay() > listToSort.get(j).
                        getCostPerDay()) {
                    swap(listToSort, j, j - 1);
                }
            }
        }
        };
        func.accept(list);
    }
}
