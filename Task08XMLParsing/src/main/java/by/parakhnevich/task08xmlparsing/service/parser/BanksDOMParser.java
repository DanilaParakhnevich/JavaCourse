package by.parakhnevich.task08xmlparsing.service.parser;

import by.parakhnevich.task08xmlparsing.bean.bank.Bank;
import by.parakhnevich.task08xmlparsing.bean.bank.StateBank;
import by.parakhnevich.task08xmlparsing.bean.deposit.Deposit;
import by.parakhnevich.task08xmlparsing.bean.deposit.Depositor;
import by.parakhnevich.task08xmlparsing.bean.deposit.TypeOfDeposit;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BanksDOMParser implements BanksParser{
    @Override
    public List<Bank> execute(File file)  throws IOException, SAXException {
        List<Bank> banks = new ArrayList<>();
        DOMParser parser = new DOMParser();
        Deposit deposit;
        Depositor depositor;
        parser.parse(file.getAbsolutePath());
        Document document = parser.getDocument();
        document.getDocumentElement().normalize();
        NodeList stateBanksList = document.getElementsByTagName("stateBank");
        for (int i = 0; i < stateBanksList.getLength(); ++i) {
            Bank bank = new StateBank();
            Node node = stateBanksList.item(i);
            if (node.getNodeType() == node.ELEMENT_NODE) {
                Element element = (Element) node;
                bank.setCountry(element.getElementsByTagName("country").
                        item(0).getTextContent());
                NodeList listDeposits = ((Element) node).getElementsByTagName("deposit");
                for (int j = 0; j < listDeposits.getLength(); ++j) {
                    Element depositElement = (Element) listDeposits.item(j);
                    deposit = new Deposit();
                    depositor = new Depositor();
                    deposit.setInvestment(Double.parseDouble(
                          getSingleChildText(depositElement, "investment")));
                    deposit.setId(Long.parseLong(depositElement.getAttribute("id")));
                    deposit.setType(TypeOfDeposit.valueOf(getSingleChildText(
                            depositElement, "type")));
                    deposit.setTimeBegin(getSingleChildText(
                            depositElement, "timeBegin"));
                    deposit.setTimeEnd(getSingleChildText(
                            depositElement, "timeEnd"));
                    deposit.setProfitability(Double.parseDouble(getSingleChildText(
                            depositElement, "profitability")));
                    depositor.setName(getSingleChildText(depositElement, "name"));
                    depositor.setAccountId(Long.parseLong(
                           ((Element) depositElement.getElementsByTagName("depositor").
                                    item(0)).getAttribute("accountId")));
                    deposit.setDepositor(depositor);
                    bank.getDepositList().add(deposit);
                }
                banks.add(bank);
            }
        }
        return banks;
    }

    private static String getSingleChildText(Element element, String childName){
        NodeList nodeList = element.getElementsByTagName(childName);
        return nodeList.item(0).getTextContent();
    }
}
