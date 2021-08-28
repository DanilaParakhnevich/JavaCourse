package by.parakhnevich.oop.service.calculate;

import by.parakhnevich.oop.bean.voucher.CruiseVoucher;
import by.parakhnevich.oop.bean.voucher.Voucher;

/**
 * Class return full cost of service of voucher
 * @autor Danila Parakhnevich
 * @version 1.1
 */
public class CalculateFullCost {
    double costPerTransport;
    double costPerFood;

    public double calculate(Voucher voucher) {
        switch (voucher.getTypeOfFood()){
           case ("AI"):
               costPerFood = 50;
               break;
           case ("BF"):
               costPerFood = 30;
               break;
           case("Breakfasts"):
               costPerFood = 10;
               break;
            default:
                costPerFood = 0;
        }
        if (voucher instanceof CruiseVoucher) {
            return (voucher.getCostPerDay() + costPerFood)
                    * voucher.getCountOfDays();
        }
        switch (voucher.getTypeOfTransport()){
           case ("Ship"):
               costPerTransport = 100;
               break;
           case ("Bus"):
               costPerTransport = 50;
               break;
           case ("Plain"):
               costPerTransport = 150;
               break;
            default:
                costPerTransport = 0;
        }
        return (costPerTransport + costPerFood + voucher.getCostPerDay()) *
                voucher.getCountOfDays();
    }
}
