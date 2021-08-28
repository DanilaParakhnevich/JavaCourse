package by.parakhnevich.oop.bean.voucher;

/**
 * Inheritor of Voucher - ClASS
 * @autor Danila Parakhnevich
 * @version 1.1
 * @see by.parakhnevich.oop.bean.voucher.Voucher
 */
public class CruiseVoucher extends Voucher {
    public CruiseVoucher(String country, String typeOfVoucher,
                         double costPerDay, int maxCountOfDays) {
        super(country, typeOfVoucher, costPerDay, maxCountOfDays);
        this.listOfAcceptedTransports.put(1, "Ship");
        this.listOfAcceptedFood.put(1, "AI");
        this.listOfAcceptedFood.put(2, "No food");
    }
}
