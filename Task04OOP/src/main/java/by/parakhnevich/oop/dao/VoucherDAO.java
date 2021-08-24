package by.parakhnevich.oop.dao;

import by.parakhnevich.oop.bean.voucher.Voucher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class VoucherDAO implements DAO{
    private static final String FILENAME = "info.txt";

    @Override
    public List<Voucher> getAll() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("files/" + FILENAME)).getFile());
        String absolutePath = file.getAbsolutePath();
        List<String> list = Files.lines(Paths.get(absolutePath)).collect(Collectors.toList());
        List<Voucher> result = new ArrayList<>();
        for (String element : list) {
            String[] array = element.split(" ");
            Voucher.Passport passport = new IdentifyType().get(array[1]);
            result.add(passport.country(array[0]).
                    typeOfVoucher(array[1]).costPerDay(Double.parseDouble(array[2])).
                    maxCountOfDays(Integer.parseInt(array[3])).
                    built());
        }
        return result;
    }
}
