package by.parakhnevich.oop.service.print;

import by.parakhnevich.oop.bean.voucher.Voucher;

import java.util.List;

/**
 * @autor Danila Parakhnevich
 * @version 1.1
 * @see by.parakhnevich.oop.service.print.SortViaCountry
 * @see by.parakhnevich.oop.service.print.SortViaTypeVoucher
 */
public interface SortVoucherWithChoise {
    public void sort(List<Voucher> list, String obj);
}
