package by.parakhnevich.oop.service.choose;

import by.parakhnevich.oop.bean.voucher.Voucher;
import by.parakhnevich.oop.service.print.PrintService;

/**
 * Class return list of available types of food in
 * vouchers
 * @autor Danila Parakhnevich
 * @version 1.1
 */
public class ChooseFood {
    public String choose(Voucher voucher) {
        return new PrintService().get(voucher.getListOfAcceptedFood());
    }
}
