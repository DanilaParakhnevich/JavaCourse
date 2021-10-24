package by.parakhnevich.task08xmlparsing.service.handler;

import by.parakhnevich.task08xmlparsing.bean.bank.Bank;
import by.parakhnevich.task08xmlparsing.bean.bank.BanksTagName;
import by.parakhnevich.task08xmlparsing.bean.bank.CommercialBank;
import by.parakhnevich.task08xmlparsing.bean.bank.StateBank;
import by.parakhnevich.task08xmlparsing.bean.deposit.Deposit;
import by.parakhnevich.task08xmlparsing.bean.deposit.Depositor;
import by.parakhnevich.task08xmlparsing.bean.deposit.TypeOfDeposit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BanksSaxHandler extends DefaultHandler {
    private final List<Bank> banks = new ArrayList<>();
    private StringBuilder text;
    private final Logger logger = LogManager.getLogger(BanksSaxHandler.class);
    Bank bank;
    Deposit deposit;
    Depositor depositor;
    private static final String ERROR = "MISTAKE IN XML FILE";
    public List<Bank> getBanks() {
        return banks;
    }

    @Override
    public void startDocument() {
        logger.info("Parsing started.");
    }

    @Override
    public void endDocument() {
        logger.info("Parsing ended.");
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {
        String toLog = "startElement -> " + "uri: " + uri + ", localName: "
                + localName + ", qName: " + qName;
        logger.info(toLog);
        text = new StringBuilder();
        switch (qName) {
            case "commercialBank":
                bank = new CommercialBank();
                break;
            case "stateBank":
                bank = new StateBank();
                break;
            case "deposit":
                deposit = new Deposit();
                deposit.setId(Long.parseLong(attributes.getValue("id")));
                break;
            case "depositor":
                depositor = new Depositor();
                depositor.setAccountId(Long.parseLong(
                        attributes.getValue("accountId")));
                break;
            default:
                logger.error(ERROR);
        }
    }

    @Override
    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String toLog = "endElement -> " + "uri: " + uri + ", localName: "
                + localName + ", qName: " + qName;
        logger.info(toLog);
        BanksTagName tagName = BanksTagName.
                valueOf(qName.toUpperCase().replace("-", "_"));
        switch (tagName) {
            case COMMERCIALBANK:
            case STATEBANK:
                System.out.println(bank.toString());
                banks.add(bank);
                bank = null;
                break;
            case COUNTRY:
                bank.setCountry(text.toString());
                break;
            case NAME:
                depositor.setName(text.toString());
                break;
            case TYPE:
                deposit.setType(TypeOfDeposit.
                        valueOf(text.toString().toUpperCase()));
                break;
            case INVESTMENT:
                deposit.setInvestment(Double.parseDouble(text.toString()));
                break;
            case PROFITABILITY:
                deposit.setProfitability(Double.parseDouble(text.toString()));
                break;
            case TIMEBEGIN:
                deposit.setTimeBegin(text.toString());
                break;
            case TIMEEND:
                deposit.setTimeEnd(text.toString());
                break;
            case DEPOSITOR:
                deposit.setDepositor(depositor);
                depositor = null;
                break;
            case DEPOSIT:
                bank.getDepositList().add(deposit);
                deposit = null;
                break;
            case BANKS:
                logger.info("BANKS ARE END CHECKING");
                break;
            case OWNER:
                ((CommercialBank)bank).setOwner(text.toString());
                break;
            default:
                logger.error(ERROR);
        }
    }


    @Override
    public void warning(SAXParseException exception) {
        String toLog = "WARNING: line " + exception.getLineNumber() + ": "
                + exception.getMessage();
        logger.warn(toLog);
    }

    @Override
    public void error(SAXParseException exception) {
        String toLog = "ERROR: line " + exception.getLineNumber() + ": "
                + exception.getMessage();
        logger.error(toLog);
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        String toLog = "FATAL: line " + exception.getLineNumber() + ": "
                + exception.getMessage();
        logger.fatal(toLog);
        throw (exception);
    }

}
