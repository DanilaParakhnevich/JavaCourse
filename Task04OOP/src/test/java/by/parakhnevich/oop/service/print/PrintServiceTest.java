package by.parakhnevich.oop.service.print;

import by.parakhnevich.oop.bean.voucher.Voucher;
import by.parakhnevich.oop.dao.VoucherDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PrintServiceTest {
    PrintService service = new PrintService();

    @Test
    void checkCorrectness1Test() throws IOException {
        List<Voucher> list = new VoucherDAO().getAll(1);
        assertEquals(service.get(list.get(0).getListOfAcceptedFood()),
                "1.AI" + '\n' +
        "2.No food" + '\n');
    }
    @Test
    void checkCorrectness2Test() throws IOException {
        List<Voucher> list = new VoucherDAO().getAll(1);
        assertEquals(service.get(list.get(0).getListOfAcceptedTransports()),
                "1.Ship" + '\n');
    }
}