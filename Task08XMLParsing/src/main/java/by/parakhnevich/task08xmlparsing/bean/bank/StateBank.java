package by.parakhnevich.task08xmlparsing.bean.bank;

import by.parakhnevich.task08xmlparsing.bean.bank.Bank;

public class StateBank extends Bank {
    public StateBank() {
        super();
    }

    @Override
    public String toString() {
        return "State bank" + '\n' + super.toString();
    }
}
