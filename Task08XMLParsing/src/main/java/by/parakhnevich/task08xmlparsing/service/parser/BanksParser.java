package by.parakhnevich.task08xmlparsing.service.parser;

import by.parakhnevich.task08xmlparsing.bean.bank.Bank;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface BanksParser {
    public List<Bank> execute(File file) throws IOException, SAXException, XMLStreamException;
}
