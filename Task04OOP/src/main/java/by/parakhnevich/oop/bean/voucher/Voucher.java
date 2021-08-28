package by.parakhnevich.oop.bean.voucher;

import by.parakhnevich.oop.view.locale.LocaleSingleton;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class Voucher is a class from which are inherited other
 * certain classes - Vouchers . Voucher is a class-body.
 * @autor Danila Parakhnevich
 * @version 1.1
 */
public class Voucher {
    protected final Map<Integer, String> listOfAcceptedTransports = new HashMap<>();
    protected final Map<Integer, String> listOfAcceptedFood = new HashMap<>();

    private final String country;
    private final String typeOfVoucher;
    private final int maxCountOfDays;
    private int countOfDays;
    private double costPerDay;
    private String typeOfTransport;
    private String typeOfFood;


    public Voucher(String country, String typeOfVoucher,
                   double costPerDay, int maxCountOfDays){
        this.maxCountOfDays = maxCountOfDays;
        this.typeOfVoucher = typeOfVoucher;
        this.country = country;
        this.costPerDay = costPerDay;
    }



    public String getCountry() {
        return country;
    }

    public String getTypeOfVoucher() {
        return typeOfVoucher;
    }

    public int getMaxCountOfDays() {
        return maxCountOfDays;
    }
    public int getCountOfDays() {
        return countOfDays;
    }

    public void setCountOfDays(int countOfDays) {
        this.countOfDays = countOfDays;
    }

    public double getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(double costPerDay) {
        this.costPerDay = costPerDay;
    }

    public String getTypeOfTransport() {
        return typeOfTransport;
    }

    public void setTypeOfTransport(String typeOfTransport) {
        this.typeOfTransport = typeOfTransport;
    }

    public String getTypeOfFood() {
        return typeOfFood;
    }

    public void setTypeOfFood(java.lang.String typeOfFood) {
        this.typeOfFood = typeOfFood;
    }

    public String get(){
        return country + ", " + typeOfVoucher + ", " + costPerDay + " $, " + maxCountOfDays +
              " " + LocaleSingleton.getResourceBundle().getString("DAYS");
    }

    public Map<Integer, String> getListOfAcceptedTransports() {
        return listOfAcceptedTransports;
    }

    public Map<Integer, String> getListOfAcceptedFood() {
        return listOfAcceptedFood;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voucher voucher = (Voucher) o;
        return maxCountOfDays == voucher.maxCountOfDays &&
            countOfDays == voucher.countOfDays &&
            Double.compare(voucher.costPerDay, costPerDay) == 0 &&
            Objects.equals(listOfAcceptedTransports, voucher.listOfAcceptedTransports) &&
            Objects.equals(listOfAcceptedFood, voucher.listOfAcceptedFood) &&
            Objects.equals(country, voucher.country) &&
            Objects.equals(typeOfVoucher, voucher.typeOfVoucher) &&
            Objects.equals(typeOfTransport, voucher.typeOfTransport) &&
            Objects.equals(typeOfFood, voucher.typeOfFood);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listOfAcceptedTransports, listOfAcceptedFood, country, typeOfVoucher, maxCountOfDays, countOfDays, costPerDay, typeOfTransport, typeOfFood);
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "listOfAcceptedTransports=" + listOfAcceptedTransports +
                ", listOfAcceptedFood=" + listOfAcceptedFood +
                ", country='" + country + '\'' +
                ", typeOfVoucher='" + typeOfVoucher + '\'' +
                ", maxCountOfDays=" + maxCountOfDays +
                ", countOfDays=" + countOfDays +
                ", costPerDay=" + costPerDay +
                ", typeOfTransport='" + typeOfTransport + '\'' +
                ", typeOfFood='" + typeOfFood + '\'' +
                '}';
    }
}
