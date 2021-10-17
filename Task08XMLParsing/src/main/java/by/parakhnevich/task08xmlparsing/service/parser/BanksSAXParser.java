package by.parakhnevich.task08xmlparsing.service.parser;

import by.parakhnevich.task08xmlparsing.bean.bank.Bank;
import by.parakhnevich.task08xmlparsing.service.handler.BanksSaxHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BanksSAXParser implements BanksParser{
    XMLReader reader;
    BanksSaxHandler handler = new BanksSaxHandler();

    public BanksSAXParser() throws ParserConfigurationException, SAXException {
         reader = XMLReaderFactory.createXMLReader();
    }

    public List<Bank> execute(File file) throws IOException, SAXException {
        reader.setContentHandler(handler);
        reader.parse(new InputSource(file.getAbsolutePath()));


        reader.setFeature("http://xml.org/sax/features/validation", true);
        reader.setFeature("http://xml.org/sax/features/namespaces", true);
        reader.setFeature("http://xml.org/sax/features/string-interning", true);
        reader.setFeature("http://apache.org/xml/features/validation/schema",false);
        return handler.getBanks();
    }
}
