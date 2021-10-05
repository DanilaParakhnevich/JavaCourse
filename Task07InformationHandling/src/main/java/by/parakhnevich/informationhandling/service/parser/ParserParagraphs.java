package by.parakhnevich.informationhandling.service.parser;

import by.parakhnevich.informationhandling.bean.partsoftext.Composite;
import by.parakhnevich.informationhandling.bean.partsoftext.Paragraph;

import java.util.ArrayList;
import java.util.List;

public class ParserParagraphs extends TextHandler {
    public ParserParagraphs() {
        this.parserHandler = new ParserSentences();
    }

    @Override
    public List<Composite> parse(String text) {
        List<Composite> listOfParagraphs = new ArrayList<>();
        String[] arrayOfParagraphs = text.split("(?m)(?=^\\s{4})");
        for (String arrayOfParagraph : arrayOfParagraphs) {
            listOfParagraphs.add(new Paragraph(parserHandler.parse(arrayOfParagraph)));
        }
        return listOfParagraphs;
    }
}
