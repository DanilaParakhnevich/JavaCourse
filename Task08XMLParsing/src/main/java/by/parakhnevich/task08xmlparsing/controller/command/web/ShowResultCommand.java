package by.parakhnevich.task08xmlparsing.controller.command.web;

import by.parakhnevich.task08xmlparsing.bean.bank.Bank;
import by.parakhnevich.task08xmlparsing.service.FileInResources;
import by.parakhnevich.task08xmlparsing.service.ReaderServlet;
import by.parakhnevich.task08xmlparsing.service.parser.BanksDOMParser;
import by.parakhnevich.task08xmlparsing.service.parser.BanksSAXParser;
import by.parakhnevich.task08xmlparsing.service.parser.BanksStAXParser;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ShowResultCommand implements WebCommand{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, SAXException, ParserConfigurationException, XMLStreamException, ServletException {
        File file = new FileInResources().getFile("files/copies/" + req.getSession().getAttribute("fileName"));
        List<Bank> list;
        switch (req.getSession().getAttribute("ParserType").toString()) {
            case "1":
                list = new BanksDOMParser().execute(file);
                break;
            case "2":
                list = new BanksSAXParser().execute(file);
                break;
            default:
                list = new BanksStAXParser().execute(file);
                break;
        }
        req.setAttribute("bankList", list);
        req.getRequestDispatcher("/jsp/result.jsp").forward(req, resp);
    }
}
