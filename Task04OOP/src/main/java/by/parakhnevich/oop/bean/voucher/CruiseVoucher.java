package by.parakhnevich.oop.bean.voucher;

import by.parakhnevich.oop.view.locale.LocaleSingleton;

public class CruiseVoucher extends Voucher {
    private CruiseVoucher(Voucher.Passport passport) {
        super(passport);
        this.listOfAcceptedTransports.put(1, "Ship");
        this.listOfAcceptedFood.put(1, "AI");
        this.listOfAcceptedFood.put(2, "No food");
    }
}
