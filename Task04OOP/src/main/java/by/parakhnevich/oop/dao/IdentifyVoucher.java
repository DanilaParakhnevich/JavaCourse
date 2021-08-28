package by.parakhnevich.oop.dao;

import by.parakhnevich.oop.bean.voucher.CruiseVoucher;
import by.parakhnevich.oop.bean.voucher.Voucher;

/**
 * Class return suitable Voucher
 * @autor Danila Parakhnevich
 * @version 1.1
 */
public class IdentifyVoucher {
    public Voucher get(String[] list){
        String first = list[0];
        String second = list[1];
        double third = Double.parseDouble(list[2]);
        int fourth = Integer.parseInt(list[3]);
        if (second.equals("Cruise")){
            return new CruiseVoucher(first, second,
                   third, fourth);
        }
        if (second.equals("Excursion")){
            return new CruiseVoucher(first, second,
                    third, fourth);
        }
        if (second.equals("Shopping")){
            return new CruiseVoucher(first, second,
                    third, fourth);
        }
        if (second.equals("Treatment")){
            return new CruiseVoucher(first, second,
                    third, fourth);
        }
        if (second.equals("Vacation")){
            return new CruiseVoucher(first, second,
                    third, fourth);
        }
        return null;
    }
}
