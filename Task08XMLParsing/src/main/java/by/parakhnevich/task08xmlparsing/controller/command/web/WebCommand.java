package by.parakhnevich.task08xmlparsing.controller.command.web;

import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public interface WebCommand {
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, SAXException, ParserConfigurationException, XMLStreamException, ServletException;
}
