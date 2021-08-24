package by.parakhnevich.oop.bean.voucher;


public class ShoppingVoucher extends Voucher {
    private ShoppingVoucher(Voucher.Passport passport) {
        super(passport);
        this.listOfAcceptedFood.put(1, "Breakfasts");
        this.listOfAcceptedFood.put(2, "AI");
        this.listOfAcceptedFood.put(3, "No food");
        this.listOfAcceptedTransports.put(1, "Bus");
        this.listOfAcceptedTransports.put(2, "Plain");
    }
}
