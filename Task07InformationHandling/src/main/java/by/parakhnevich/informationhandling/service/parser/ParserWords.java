package by.parakhnevich.informationhandling.service.parser;

import by.parakhnevich.informationhandling.bean.partsoftext.Composite;
import by.parakhnevich.informationhandling.bean.partsoftext.Word;
import by.parakhnevich.informationhandling.service.validator.LetterService;

import java.util.ArrayList;
import java.util.List;

public class ParserWords extends TextHandler {
    private final LetterService service = new LetterService();

    public ParserWords() {
        this.parserHandler = new ParserSymbols();
    }

    @Override
    public List<Composite> parse(String text) {
        String[] arrayOfWords = text.split("[ ]");
        List<Composite> listOfWords = new ArrayList<>();

        for (String element : arrayOfWords) {
            listOfWords.add(new Word(parserHandler.parse(element)));
        }
        return listOfWords;
    }
}
