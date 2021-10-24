package by.parakhnevich.task08xmlparsing.service.parser;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;

import static org.testng.Assert.*;

public class BanksStAXParserTest {
    BanksStAXParser parser = new BanksStAXParser();

    public BanksStAXParserTest() throws ParserConfigurationException, SAXException {
    }

    @DataProvider(name="positiveDataForTestingStAXSize")
    public Object[][] createPositiveDataForTestingStAXSize() {
        return new Object[][]{
                {new File("J:\\Projects\\JavaCourse\\Task08XMLParsing\\src\\test\\resources\\bankDeposits1.xml"), 1},
                {new File("J:\\Projects\\JavaCourse\\Task08XMLParsing\\src\\test\\resources\\bankDeposits2.xml"), 16}
        };
    }


    @Test(dataProvider = "positiveDataForTestingStAXSize")
    public void testExecuteSize(File file, int size) throws IOException, SAXException, XMLStreamException {
        Assert.assertEquals(parser.execute(file).size(), size);
    }
}