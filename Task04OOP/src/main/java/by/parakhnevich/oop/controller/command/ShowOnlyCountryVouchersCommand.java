package by.parakhnevich.oop.controller.command;

import by.parakhnevich.oop.bean.Country;
import by.parakhnevich.oop.bean.voucher.Voucher;
import by.parakhnevich.oop.service.print.ListVoucherToString;

import java.util.List;

public class ShowOnlyCountryVouchersCommand implements Command{
    Country country;
    public ShowOnlyCountryVouchersCommand(String country) {
        this.country = new Country(country);
    }

    @Override
    public String execute(List<Voucher> list) {
        list.removeIf(element -> !this.country.getName().equals(element.getCountry()));
        return new ListVoucherToString().get(list);
    }
}
