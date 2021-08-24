package by.parakhnevich.oop.dao;

import by.parakhnevich.oop.bean.voucher.Voucher;

import java.io.IOException;
import java.util.List;

public interface DAO {
    public List<Voucher> getAll() throws IOException;
}
