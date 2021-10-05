package by.parakhnevich.informationhandling.bean.partsoftext;

import by.parakhnevich.informationhandling.service.validator.LetterService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Word implements Composite{
    private final List<Composite> listOfSymbols;
    private final List<Composite> delimiter;
    LetterService service = new LetterService();

    public Word(List<Composite> listOfComposites) {
        listOfSymbols = new ArrayList<>();
        delimiter = new ArrayList<>();
        for (Composite composite : listOfComposites) {
            Symbol symbol = (Symbol) composite;
            if (!service.isLetter(symbol.getElement()) && symbol.getElement() != '-'
            && symbol.getElement() != '\'') {
                delimiter.add(symbol);
            }
            else {
                listOfSymbols.add(symbol);
            }
        }
    }

    @Override
    public Composite getChild(int index) {
        return listOfSymbols.get(index);
    }

    @Override
    public void remove(int index) {
        listOfSymbols.remove(index);
    }

    @Override
    public void add(Composite composite) {
        listOfSymbols.add(composite);
    }

    @Override
    public int getSize() {
        return listOfSymbols.size();
    }

    @Override
    public void swap(int index1, int index2) {
        Composite composite = listOfSymbols.get(index1);
        listOfSymbols.set(index1, listOfSymbols.get(index2));
        listOfSymbols.set(index2, composite);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return listOfSymbols.equals(word.listOfSymbols);
    }

    public List<Composite> getDelimiter() {
        return this.delimiter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(listOfSymbols, delimiter);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        listOfSymbols.forEach(element -> result.append(element.toString()));
        return result.toString();
    }
}
