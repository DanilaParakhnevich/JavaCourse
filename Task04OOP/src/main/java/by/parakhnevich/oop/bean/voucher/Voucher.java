package by.parakhnevich.oop.bean.voucher;

import java.util.Map;

public class Voucher {
    Map<Integer, String> listOfAcceptedTransports;
    Map<Integer, String> listOfAcceptedFood;

    private final String country;
    private final String typeOfVoucher;
    private final int maxCountOfDays;
    private int countOfDays;
    private double costPerDay;
    private String typeOfTransport;
    private String typeOfFood;


    public static class Passport {
        private String country;
        private String typeOfVoucher;
        private int maxCountOfDays;
        private double costPerDay;

        public Passport costPerDay(double value) {
            this.costPerDay = value;
            return this;
        }

        public Passport country(String value) {
            this.country = value;
            return this;
        }
        public Passport typeOfVoucher(String value) {
            this.typeOfVoucher = value;
            return this;
        }
        public Passport maxCountOfDays(int value) {
            this.maxCountOfDays = value;
            return this;
        }

        public Voucher built() {
            return new Voucher(this);
        }

        public double getCostPerDay() {
            return costPerDay;
        }
    }

    protected Voucher(Passport passport) {
        maxCountOfDays = passport.maxCountOfDays;
        typeOfVoucher = passport.typeOfVoucher;
        country = passport.country;
        costPerDay = passport.costPerDay;
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

    public void setTypeOfFood(String typeOfFood) {
        this.typeOfFood = typeOfFood;
    }

    public String get(){
        return country + " " + typeOfVoucher + " " + costPerDay + " " + maxCountOfDays;
    }
}
