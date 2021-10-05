package by.parakhnevich.informationhandling.service.parser;

import by.parakhnevich.informationhandling.bean.partsoftext.Composite;

import java.util.List;

public abstract class TextHandler {
    TextHandler parserHandler;

    public abstract List<Composite> parse(String text);
}
