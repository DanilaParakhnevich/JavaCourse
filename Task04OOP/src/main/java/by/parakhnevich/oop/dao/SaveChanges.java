package by.parakhnevich.oop.dao;

import by.parakhnevich.oop.bean.voucher.Voucher;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Class save list of Vouchers to file
 * @autor Danila Parakhnevich
 * @version 1.1
 */
public class SaveChanges {
    public void save(List<Voucher> list) {
        String filename = "saved.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("files/" + filename)).getFile());
        String absolutePath = file.getAbsolutePath();
        try(FileWriter fileWriter = new FileWriter(new File(absolutePath))) {
            for (Voucher element : list) {
                fileWriter.write(element.getCountry() + " " + element.getTypeOfVoucher() +
                        " " + element.getCostPerDay() + " " + element.getMaxCountOfDays() + '\n');
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
