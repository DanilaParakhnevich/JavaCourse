package by.parakhnevich.task08xmlparsing.controller.command;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public interface Command {
    public String execute(List<String> list) throws XMLStreamException, IOException, SAXException, ParserConfigurationException;
}
