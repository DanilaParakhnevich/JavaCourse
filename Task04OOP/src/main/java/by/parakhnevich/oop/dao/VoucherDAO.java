package by.parakhnevich.oop.dao;

import by.parakhnevich.oop.bean.voucher.Voucher;
import by.parakhnevich.oop.controller.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * VoucherDAO read from file all necessary info about
 * Vouchers and parse it to list of Voucher's
 * @autor Danila Parakhnevich
 * @version 1.1
 */
public class VoucherDAO {
    public List<Voucher> getAll(int index) throws IOException {
        String filename = index == 1 ? "info.txt" : "saved.txt";
        //first file is file with default values , second is file with user's correcting
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("files/" + filename)).getFile());
        String absolutePath = file.getAbsolutePath();
        List<String> list = Files.lines(Paths.get(absolutePath)).collect(Collectors.toList());
        List<Voucher> result = new ArrayList<>();
        for (String element : list) {
            String[] array = (element.split(" "));
            result.add(new IdentifyVoucher().get(array));
        }
        return result;
    }
}
