package by.parakhnevich.task08xmlparsing.bean.deposit;

import java.util.Objects;

public class Deposit {
    private TypeOfDeposit type;
    private Depositor depositor;
    private double investment;
    private double profitability;
    private String timeBegin;
    private String timeEnd;
    private long id;

    public TypeOfDeposit getType() {
        return type;
    }

    public void setType(TypeOfDeposit type) {
        this.type = type;
    }

    public Depositor getDepositor() {
        return depositor;
    }

    public void setDepositor(Depositor depositor) {
        this.depositor = depositor;
    }

    public double getInvestment() {
        return investment;
    }

    public void setInvestment(double investment) {
        this.investment = investment;
    }

    public double getProfitability() {
        return profitability;
    }

    public void setProfitability(double profitability) {
        this.profitability = profitability;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deposit deposit = (Deposit) o;
        return Double.compare(deposit.investment, investment) == 0 && Double.compare(deposit.profitability, profitability) == 0 && type == deposit.type && Objects.equals(depositor, deposit.depositor) && Objects.equals(timeBegin, deposit.timeBegin) && Objects.equals(timeEnd, deposit.timeEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, depositor, investment, profitability, timeBegin, timeEnd);
    }

    @Override
    public String toString() {
        return "Deposit , id = " + id + '\n' +
                "Type of deposit = " + type + '\n' +
                "Depositor is " + depositor.toString() + '\n' +
                "Investment = " + investment + '\n' +
                "Profitability=" + profitability + '\n' +
                "Date of registration = " + timeBegin  + '\n' +
                "Date of end of deposit = " + timeEnd;
    }
}
