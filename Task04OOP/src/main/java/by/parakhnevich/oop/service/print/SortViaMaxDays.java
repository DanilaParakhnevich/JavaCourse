package by.parakhnevich.oop.service.print;
import by.parakhnevich.oop.bean.voucher.Voucher;
import java.util.List;

/**
 * Sort list of Voucher via days
 * @autor Danila Parakhnevich
 * @version 1.1
 * @see by.parakhnevich.oop.service.print.SortVoucher
 */
public class SortViaMaxDays implements SortVoucher{
@Override
    public void sort(List<Voucher> list) {
        for (int i = 0; i < list.size(); ++i){
            for (int j = 1; j < list.size() - i; ++j) {
                if (list.get(j-1).getMaxCountOfDays() > list.get(j).getMaxCountOfDays()){
                    swap(list, j, j - 1);
                }
            }
        }
    }
}
