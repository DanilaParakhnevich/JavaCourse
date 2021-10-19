package by.parakhnevich.task08xmlparsing.controller;

import by.parakhnevich.task08xmlparsing.controller.command.Command;
import by.parakhnevich.task08xmlparsing.controller.command.PrintDOMConsoleCommand;
import by.parakhnevich.task08xmlparsing.controller.command.PrintSAXConsoleCommand;
import by.parakhnevich.task08xmlparsing.controller.command.PrintStAXConsoleCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    Logger logger = LogManager.getLogger(Controller.class);
    Map<String, Command> commandMap = new HashMap<>();
    private static final String DOM = "CONSOLE_PRINT_DOM";
    private static final String SAX = "CONSOLE_PRINT_SAX";
    private static final String STAX = "CONSOLE_PRINT_STAX";

    public Controller() {
        commandMap.put(DOM, new PrintDOMConsoleCommand());
        commandMap.put(SAX, new PrintSAXConsoleCommand());
        commandMap.put(STAX, new PrintStAXConsoleCommand());
    }

    public String execute(List<String> list) throws IOException {
        try {
            return commandMap.get(list.remove(0)).execute(list);
        } catch (XMLStreamException | SAXException | ParserConfigurationException e) {
            logger.error(e);
        }
        return null;
    }
}
