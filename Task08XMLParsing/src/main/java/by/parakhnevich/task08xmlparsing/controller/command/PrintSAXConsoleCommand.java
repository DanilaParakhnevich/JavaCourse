package by.parakhnevich.task08xmlparsing.controller.command;

import by.parakhnevich.task08xmlparsing.bean.bank.Bank;
import by.parakhnevich.task08xmlparsing.service.parser.BanksSAXParser;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PrintSAXConsoleCommand implements Command {
    private static final String CONSTANT = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    @Override
    public String execute(List<String> list) throws ParserConfigurationException, SAXException, IOException {

        SchemaFactory xsdFactory = SchemaFactory.newInstance(CONSTANT);


        StringBuilder builder = new StringBuilder();
        List<Bank> banks;
        banks = new BanksSAXParser().execute(new File(list.get(0)));
        for (Bank bank : banks) {
            builder.append(bank.toString()).append('\n');
        }
        return builder.toString();
    }
}
