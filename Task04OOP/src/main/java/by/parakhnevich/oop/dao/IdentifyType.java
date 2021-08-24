package by.parakhnevich.oop.dao;

import by.parakhnevich.oop.bean.voucher.*;

public class IdentifyType {
    public Voucher.Passport get(String type) {
        switch (type){
            case ("Cruise"):
                return new CruiseVoucher.Passport();
            case ("Shopping"):
                return new ShoppingVoucher.Passport();
            case ("Treatment"):
                return new TreatmentVoucher.Passport();
            case ("Excursion"):
                return new ExcursionVoucher.Passport();
            case ("Vacation"):
                return new VacationVoucher.Passport();
        }
        return null;
    }
}
