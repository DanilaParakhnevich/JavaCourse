package by.parakhnevich.oop.service.calculate;

import by.parakhnevich.oop.bean.voucher.CruiseVoucher;
import by.parakhnevich.oop.bean.voucher.Voucher;

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
           case ("No food"):
               costPerFood = 0;
               break;
           case("Breakfasts"):
               costPerFood = 10;
               break;
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
       }
        if (voucher instanceof CruiseVoucher) {
            return (voucher.getCostPerDay() + costPerFood)
                    * voucher.getCountOfDays();
        }
        return (costPerTransport + costPerFood + voucher.getCostPerDay()) *
                voucher.getCountOfDays();
    }
}
