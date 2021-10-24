package by.parakhnevich.task08xmlparsing.service.parser;

import by.parakhnevich.task08xmlparsing.bean.bank.Bank;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

public class BanksSAXParserTest {
    BanksSAXParser parser = new BanksSAXParser();

    public BanksSAXParserTest() throws ParserConfigurationException, SAXException {
    }

    @DataProvider(name="positiveDataForTestingSAXSize")
    public Object[][] createPositiveDataForTestingSAXSize() {
        return new Object[][]{
                {new File("J:\\Projects\\JavaCourse\\Task08XMLParsing\\src\\test\\resources\\bankDeposits1.xml"), 1},
                {new File("J:\\Projects\\JavaCourse\\Task08XMLParsing\\src\\test\\resources\\bankDeposits2.xml"), 17}
        };
    }


    @Test(dataProvider = "positiveDataForTestingSAXSize")
    public void testExecuteSize(File file, int size) throws IOException, SAXException {
        Assert.assertEquals(parser.execute(file).size(), size);
    }
}