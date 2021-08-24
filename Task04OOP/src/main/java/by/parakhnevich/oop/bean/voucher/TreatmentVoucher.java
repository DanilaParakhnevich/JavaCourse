package by.parakhnevich.oop.bean.voucher;

public class TreatmentVoucher extends Voucher {
    private TreatmentVoucher(Voucher.Passport passport) {
        super(passport);
        this.listOfAcceptedFood.put(1, "FB");
        this.listOfAcceptedTransports.put(1, "Bus");
        this.listOfAcceptedTransports.put(2, "Plain");
    }
}
