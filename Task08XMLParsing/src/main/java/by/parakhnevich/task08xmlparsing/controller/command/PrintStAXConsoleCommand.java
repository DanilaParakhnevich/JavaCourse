package by.parakhnevich.task08xmlparsing.controller.command;

import by.parakhnevich.task08xmlparsing.bean.bank.Bank;
import by.parakhnevich.task08xmlparsing.service.parser.BanksStAXParser;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PrintStAXConsoleCommand implements Command {
    @Override
    public String execute(List<String> list) throws XMLStreamException, IOException, SAXException {
        List<Bank> listOfBanks = new BanksStAXParser().execute(new File(list.get(0)));
        StringBuilder builder = new StringBuilder();
        for (Bank bank : listOfBanks) {
            builder.append(bank.toString()).append('\n');
        }
        return builder.toString();
    }
}
