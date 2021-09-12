package by.parakhnevich.multithreadingmatrix.dao;

public class LimitsValidator {
    public boolean validate(int number, int lowNumber, int highNumber) {
        return number >= lowNumber && number <= highNumber;
    }
}
