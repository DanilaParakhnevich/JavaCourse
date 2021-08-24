package by.parakhnevich.oop.service.print;

import by.parakhnevich.oop.bean.voucher.Voucher;

import java.util.List;

public interface SortVoucher {
    public void sort(List<Voucher> list);

    default void swap(List<Voucher> list, int i, int j) {
        Voucher voucher = list.get(i);
        list.set(i, list.get(j));
        list.set(j, voucher);
    }
}
