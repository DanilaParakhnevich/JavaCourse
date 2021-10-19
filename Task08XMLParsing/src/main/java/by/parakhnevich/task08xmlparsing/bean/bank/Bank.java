package by.parakhnevich.task08xmlparsing.bean.bank;

import by.parakhnevich.task08xmlparsing.bean.deposit.Deposit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bank {
    protected String country;
    protected List<Deposit> depositList = new ArrayList<>();

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Deposit> getDepositList() {
        return depositList;
    }

    public void setDepositList(List<Deposit> depositList) {
        this.depositList = depositList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(country, bank.country) && Objects.equals(depositList, bank.depositList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, depositList);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Country : ").append(country).append('\n');
        for (Deposit element : depositList) {
            builder.append(element.toString()).append('\n');
        }
        return builder.toString();
    }

    public boolean isCommercial() {
        return getClass().equals(CommercialBank.class);
    }
}
