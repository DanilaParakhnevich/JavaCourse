package by.parakhnevich.oop.bean.voucher;

import by.parakhnevich.oop.view.locale.LocaleSingleton;

public class ExcursionVoucher extends Voucher {
    private ExcursionVoucher(Voucher.Passport passport) {
        super(passport);
        this.listOfAcceptedFood.put(1, "Breakfasts");
        this.listOfAcceptedFood.put(2, "No food");
        this.listOfAcceptedTransports.put(1, "Bus");
        this.listOfAcceptedTransports.put(2, "Ship");
    }
}
