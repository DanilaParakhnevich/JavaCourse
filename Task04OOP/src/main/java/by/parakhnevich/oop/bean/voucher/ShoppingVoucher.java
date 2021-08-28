package by.parakhnevich.oop.bean.voucher;

/**
 * Inheritor of Voucher - ClASS
 * @autor Danila Parakhnevich
 * @version 1.1
 * @see by.parakhnevich.oop.bean.voucher.Voucher
 */
public class ShoppingVoucher extends Voucher {
    public ShoppingVoucher(String country, String typeOfVoucher,
                         double costPerDay, int maxCountOfDays) {
        super(country, typeOfVoucher, costPerDay, maxCountOfDays);
        this.listOfAcceptedFood.put(1, "Breakfasts");
        this.listOfAcceptedFood.put(2, "AI");
        this.listOfAcceptedFood.put(3, "No food");
        this.listOfAcceptedTransports.put(1, "Bus");
        this.listOfAcceptedTransports.put(2, "Plain");
    }
}
