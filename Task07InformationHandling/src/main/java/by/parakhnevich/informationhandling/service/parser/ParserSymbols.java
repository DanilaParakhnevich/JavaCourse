package by.parakhnevich.informationhandling.service.parser;

import by.parakhnevich.informationhandling.bean.partsoftext.Composite;
import by.parakhnevich.informationhandling.bean.partsoftext.Symbol;

import java.util.ArrayList;
import java.util.List;

public class ParserSymbols extends TextHandler {
    public ParserSymbols() {
        this.parserHandler = null;
    }

    @Override
    public List<Composite> parse(String text) {
        char[] array = text.toCharArray();
        List<Composite> listOfSymbols = new ArrayList<>();
        for (char element : array) {
            listOfSymbols.add(new Symbol(element));
        }
        return listOfSymbols;
    }
}
