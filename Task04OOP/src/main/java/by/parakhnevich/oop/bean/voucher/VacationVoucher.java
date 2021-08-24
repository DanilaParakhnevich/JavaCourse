package by.parakhnevich.oop.bean.voucher;

public class VacationVoucher extends Voucher {
    private VacationVoucher(Voucher.Passport passport){
        super(passport);
        this.listOfAcceptedFood.put(1, "Breakfasts");
        this.listOfAcceptedFood.put(2, "AI");
        this.listOfAcceptedFood.put(3, "No food");
        listOfAcceptedFood.put(4, "BF");
        this.listOfAcceptedTransports.put(1, "Bus");
        this.listOfAcceptedTransports.put(2, "Plain");
        this.listOfAcceptedTransports.put(3, "Ship");
    }
}
