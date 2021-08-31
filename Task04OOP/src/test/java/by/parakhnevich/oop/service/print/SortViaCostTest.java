package by.parakhnevich.oop.service.print;

import by.parakhnevich.oop.bean.voucher.Voucher;
import by.parakhnevich.oop.dao.VoucherDAO;
import by.parakhnevich.oop.view.ViewReader;
import by.parakhnevich.oop.view.locale.LocaleSingleton;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class SortViaCostTest {
    SortViaCost sort = new SortViaCost();

    @Test
    void checkingCorrectness() throws IOException {
        LocaleSingleton.getInstance("en","EN");
        List<Voucher> list = new VoucherDAO().getAll(1);
        sort.sort(list);
        assertEquals(new ListVoucherToString().get(list),"1. Finland, Cruise, 14.2 $, 365 max days\n" +
                "2. Belarus, Excursion, 15.3 $, 12 max days\n" +
                "3. Ukraine, Excursion, 20.1 $, 10 max days\n" +
                "4. Finland, Cruise, 23.2 $, 365 max days\n" +
                "5. Belarus, Cruise, 30.0 $, 100 max days\n" +
                "6. Finland, Vacation, 31.4 $, 15 max days\n" +
                "7. Russia, Treatment, 32.0 $, 31 max days\n" +
                "8. France, Shopping, 60.1 $, 7 max days\n" +
                "9. Italy, Shopping, 65.5 $, 7 max days\n" +
                "10. Finland, Vacation, 78.3 $, 31 max days\n" );
    }
}