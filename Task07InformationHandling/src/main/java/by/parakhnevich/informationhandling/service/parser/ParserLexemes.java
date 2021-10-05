package by.parakhnevich.informationhandling.service.parser;

import by.parakhnevich.informationhandling.bean.partsoftext.Composite;
import by.parakhnevich.informationhandling.bean.partsoftext.Lexeme;
import by.parakhnevich.informationhandling.service.Evaluator;
import by.parakhnevich.informationhandling.service.validator.LetterService;

import java.util.ArrayList;
import java.util.List;

public class ParserLexemes extends TextHandler {
    LetterService service = new LetterService();
    public ParserLexemes() {
       parserHandler = new ParserWords();
    }

    @Override
    public List<Composite> parse(String text) {
        List<Composite> listOfLexemes = new ArrayList<>();
        text = text.replace('\n', 'Ь');
        String[] array = text.split("(?<=[ ])|(?<=[Ь])");
        for (String s : array) {
            s = s.replace('Ь', '\n');
            if (!s.equals(" ")) {
                Lexeme lexeme = new Lexeme(parserHandler.parse(s));
                if (service.isExpression(lexeme.toString())) {
                    lexeme = new Lexeme(parserHandler.parse(String.valueOf(
                            new Evaluator(
                            lexeme.toString().trim()).interpret(null))));
                }
                listOfLexemes.add(lexeme);
            }
        }
        return listOfLexemes;
    }
}
