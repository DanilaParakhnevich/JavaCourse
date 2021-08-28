package by.parakhnevich.oop.service.print;

import by.parakhnevich.oop.bean.voucher.Voucher;
import by.parakhnevich.oop.service.calculate.CalculateFullCost;
import by.parakhnevich.oop.view.locale.LocaleSingleton;

/**
 * Class return full necessary info about voucher
 * @autor Danila Parakhnevich
 * @version 1.1
 */
public class PrintFullVoucher {
    public String get(Voucher voucher) {
        return LocaleSingleton.getResourceBundle().getString("COUNTRY_") + " "
                + voucher.getCountry() + '\n' +
                LocaleSingleton.getResourceBundle().getString("TYPE_VOUCHER") + " "
                + voucher.getTypeOfVoucher() + '\n' +
                LocaleSingleton.getResourceBundle().getString("TYPE_FOOD") + " "
                + voucher.getTypeOfFood() + '\n' +
                LocaleSingleton.getResourceBundle().getString("TYPE_TRANSPORT") + " "
                + voucher.getTypeOfTransport() + '\n' +
                LocaleSingleton.getResourceBundle().getString("COUNT_DAYS") + " "
                + voucher.getCountOfDays() + '\n' +
                LocaleSingleton.getResourceBundle().getString("COST") + " "
                + new CalculateFullCost().calculate(voucher) + " $";
    }
}
