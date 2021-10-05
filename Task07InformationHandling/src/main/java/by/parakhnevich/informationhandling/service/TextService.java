package by.parakhnevich.informationhandling.service;

import by.parakhnevich.informationhandling.bean.partsoftext.*;
import by.parakhnevich.informationhandling.service.validator.LetterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Locale;

public class TextService {
    Logger logger = LogManager.getLogger(TextService.class);
    private static final String BAD = "Not paragraph";
    LetterService service = new LetterService();
    public void sortListOfCompositeBySize(List<Composite> listOfParagraphs) {
        if (listOfParagraphs.get(1).getClass() != Paragraph.class) {
            logger.info(BAD);
            return;
        }
        listOfParagraphs.sort(
                (o1, o2) -> o1.getSize() - o2.getSize());
    }

    public void sortWordsByLength(List<Composite> listOfParagraphs) {
        if (listOfParagraphs.get(1).getClass() != Paragraph.class) {
            logger.info(BAD);
            return;
        }
        for(Composite paragraph : listOfParagraphs) {
            for (int i = 0; i < paragraph.getSize(); ++i) {
                sortSentence(paragraph.getChild(i));
            }
        }
    }

    public void sortLexemesByRepeats(List<Composite> listOfParagraphs, Symbol symbol) {
        if (listOfParagraphs.get(1).getClass() != Paragraph.class) {
            logger.info(BAD);
            return;
        }
        for (Composite paragraph : listOfParagraphs) {
            for (int i = 0; i < paragraph.getSize(); ++i) {
                sortLexemes(paragraph.getChild(i), symbol);
            }
        }
    }

    private int countOfRepeats(Lexeme lexeme, Symbol symbol) {
        int count = 0;
        for (int i = 0; i < lexeme.getSize(); i++) {
            if (lexeme.getChild(i).equals(symbol)) {
                ++count;
            }
        }
        for (int i = 0; i < lexeme.getWord().getSize(); ++i) {
            if (lexeme.getWord().getChild(i).equals(symbol)) {
                ++count;
            }
        }
        return count;
    }

    private void sortLexemes(Composite composite, Symbol symbol) {
        Sentence sentence = (Sentence) composite;
        for (int i = 0; i < composite.getSize(); ++i) {
            for (int j = i + 1; j < composite.getSize(); ++j) {
                Lexeme lexeme1 = (Lexeme) composite.getChild(i);
                Lexeme lexeme2 = (Lexeme) composite.getChild(j);
                if ((countOfRepeats(lexeme1, symbol) >
                        countOfRepeats(lexeme2, symbol)) ||
                        (countOfRepeats(lexeme1, symbol) ==
                        countOfRepeats(lexeme2, symbol) && lexeme1.getWord().
                        toString().toLowerCase(Locale.ROOT)
                                .compareTo(lexeme2.getWord().toString().
                                        toLowerCase(Locale.ROOT)) > 0)) {
                    sentence.swap(i, j, true);
                }
            }
        }
    }

    private void sortSentence(Composite composite) {
        for (int i = 0; i < composite.getSize(); ++i) {
            for (int j = i + 1; j < composite.getSize(); ++j) {
                if (composite.getChild(i).getChild(0).getSize() < composite.getChild(j).getChild(0).getSize()) {
                    composite.swap(i, j);
                }
            }
        }
    }
}
