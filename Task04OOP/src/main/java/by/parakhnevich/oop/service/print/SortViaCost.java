package by.parakhnevich.oop.service.print;

import by.parakhnevich.oop.bean.voucher.Voucher;

import java.util.List;

public class SortViaCost implements SortVoucher {
    public void sort(List<Voucher> list) {
        for (int i = 0; i < list.size(); ++i){
            for (int j = 1; j < list.size() - i; ++j) {
                if (list.get(j-1).getCostPerDay() > list.get(j).getCostPerDay()){
                    swap(list, j, j - 1);
                }
            }
        }
    }
}
