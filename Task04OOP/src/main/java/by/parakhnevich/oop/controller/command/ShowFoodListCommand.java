package by.parakhnevich.oop.controller.command;

import by.parakhnevich.oop.dao.VoucherDAO;
import by.parakhnevich.oop.service.print.PrintService;

import java.io.IOException;
import java.util.List;

/**
 * Implementer of Command
 * @autor Danila Parakhnevich
 * @version 1.1
 * @see by.parakhnevich.oop.controller.command.Command
 */
public class ShowFoodListCommand implements Command{
    @Override
    public String execute(List<String> list) throws IOException {
        return new PrintService().get(new VoucherDAO().getAll(2).
                get(Integer.parseInt(list.get(1)) - 1).
                getListOfAcceptedFood());
    }
}
