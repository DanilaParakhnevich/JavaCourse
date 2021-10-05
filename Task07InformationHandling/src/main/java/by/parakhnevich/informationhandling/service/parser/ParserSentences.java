package by.parakhnevich.informationhandling.service.parser;


import by.parakhnevich.informationhandling.bean.partsoftext.Composite;
import by.parakhnevich.informationhandling.bean.partsoftext.Sentence;

import java.util.ArrayList;
import java.util.List;

public class ParserSentences extends TextHandler {


    public ParserSentences() {
        this.parserHandler = new ParserLexemes();
    }

    @Override
    public List<Composite> parse(String text) {
        List<Composite> listOfSentences = new ArrayList<>();
        text = text.replace("?!", "Ъ").
                replace("...", "Ь").
                replace("\n", "ЗЗЗ");
        String[] arrayOfSentences = text.
                split("(?<=(?<![A-Z])(\\u2026|[?][!]|[?]|[!]|[.]|[Ъ]|[Ь]))");
        for (String sentence : arrayOfSentences) {
            listOfSentences.add(new Sentence(parserHandler.parse(sentence.
                    trim().replace("Ъ", "?!").
                    replace("Ь", "...").
                    replace("ЗЗЗ", "\n"))));
        }
        return listOfSentences;
    }
}
