package by.parakhnevich.oop.service.calculate;

import by.parakhnevich.oop.bean.voucher.Voucher;
import by.parakhnevich.oop.dao.VoucherDAO;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CalculateFullCostTest {
    CalculateFullCost calculator = new CalculateFullCost();

    @Test
    void noFoodTest() throws IOException {
        Voucher voucher = new VoucherDAO().getAll(1).get(0);
        voucher.setCountOfDays(20);
        voucher.setTypeOfFood("No food");
        voucher.setTypeOfTransport("Ship");
        assertEquals(calculator.calculate(voucher),
                voucher.getCostPerDay() * voucher.getCountOfDays());
    }
}