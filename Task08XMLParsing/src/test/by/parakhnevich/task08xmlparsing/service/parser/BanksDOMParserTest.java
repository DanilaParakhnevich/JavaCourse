package by.parakhnevich.task08xmlparsing.service.parser;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static org.testng.Assert.*;

public class BanksDOMParserTest {
    BanksDOMParser parser = new BanksDOMParser();

    public BanksDOMParserTest() throws ParserConfigurationException, SAXException {
    }

    @DataProvider(name="positiveDataForTestingDOMSize")
    public Object[][] createPositiveDataForTestingDOMSize() {
        return new Object[][]{
                {new File("J:\\Projects\\JavaCourse\\Task08XMLParsing\\src\\test\\resources\\bankDeposits1.xml"), 1},
                {new File("J:\\Projects\\JavaCourse\\Task08XMLParsing\\src\\test\\resources\\bankDeposits2.xml"), 16}
        };
    }


    @Test(dataProvider = "positiveDataForTestingDOMSize")
    public void testExecuteSize(File file, int size) throws IOException, SAXException {
        Assert.assertEquals(parser.execute(file).size(), size);
    }
}