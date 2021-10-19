package by.parakhnevich.task08xmlparsing.service.parser;

import by.parakhnevich.task08xmlparsing.bean.bank.Bank;
import by.parakhnevich.task08xmlparsing.bean.bank.BanksTagName;
import by.parakhnevich.task08xmlparsing.bean.bank.CommercialBank;
import by.parakhnevich.task08xmlparsing.bean.bank.StateBank;
import by.parakhnevich.task08xmlparsing.bean.deposit.Deposit;
import by.parakhnevich.task08xmlparsing.bean.deposit.Depositor;
import by.parakhnevich.task08xmlparsing.bean.deposit.TypeOfDeposit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BanksStAXParser implements BanksParser{
    BanksTagName banksTagName = null;
    Logger logger = (Logger) LogManager.getLogger(BanksDOMParser.class);

    @Override
    public List<Bank> execute(File file) throws IOException, SAXException, XMLStreamException {
        logger.info("Start parsing");
        List<Bank> list = new ArrayList<>();
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        InputStream input = new FileInputStream(file.getAbsolutePath());
        XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
        Bank bank = new CommercialBank();
        Deposit deposit = new Deposit();
        Depositor depositor = new Depositor();
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    banksTagName = BanksTagName.valueOf(reader.getLocalName().toUpperCase());
                    switch (banksTagName) {
                        case COMMERCIALBANK:
                            bank = new CommercialBank();
                            break;
                        case STATEBANK:
                            bank = new StateBank();
                            break;
                        case DEPOSIT:
                            deposit = new Deposit();
                            deposit.setId(Long.parseLong(reader.
                                    getAttributeValue(null, "id")));
                            break;
                        case DEPOSITOR:
                            depositor = new Depositor();
                            depositor.setAccountId(Long.parseLong(reader.
                                    getAttributeValue(null, "accountId")));
                            break;
                        default:
                            break;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty() || banksTagName==null) {
                        break;
                    }
                    switch (banksTagName) {
                        case COUNTRY:
                            bank.setCountry(text);
                            break;
                        case OWNER:
                            ((CommercialBank) bank).setOwner(text);
                            break;
                        case TYPE:
                            deposit.setType(TypeOfDeposit.
                                    valueOf(text.toUpperCase()));
                            break;
                        case NAME:
                            depositor.setName(text);
                            break;
                        case TIMEEND:
                            deposit.setTimeEnd(text);
                            break;
                        case TIMEBEGIN:
                            deposit.setTimeBegin(text);
                            break;
                        case PROFITABILITY:
                            deposit.setProfitability(Double.parseDouble(text));
                            break;
                        case INVESTMENT:
                            deposit.setInvestment(Double.parseDouble(text));
                            break;
                        default:
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    banksTagName = BanksTagName.valueOf(reader.getLocalName().toUpperCase());
                    switch (banksTagName) {
                        case COMMERCIALBANK:
                        case STATEBANK:
                            list.add(bank);
                            break;
                        case DEPOSIT:
                            bank.getDepositList().add(deposit);
                            break;
                        case DEPOSITOR:
                            deposit.setDepositor(depositor);
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        logger.info("End parsing. Success");
        return list;
    }
}
