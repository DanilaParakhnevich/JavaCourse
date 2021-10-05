package by.parakhnevich.informationhandling.bean.partsoftext;


import java.util.List;
import java.util.Objects;

public class Lexeme implements Composite{
    private Word word;
    private final List<Composite> listOfSymbols;

    public Lexeme (List<Composite> word) {
        Word word1 = (Word) word.get(0);
        this.word = word1;
        this.listOfSymbols = word1.getDelimiter();
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
    public void swap(int index1, int index2) {
        Composite composite = listOfSymbols.get(index1);
        listOfSymbols.set(index1, listOfSymbols.get(index2));
        listOfSymbols.set(index2, composite);
    }

    @Override
    public void add(Composite composite) {
        listOfSymbols.add(composite);
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Word getWord() {
        return word;
    }

    @Override
    public int getSize() {
        return listOfSymbols.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lexeme lexeme = (Lexeme) o;
        return listOfSymbols.equals(lexeme.listOfSymbols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listOfSymbols);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String temp = "";
        for (Composite symbol : listOfSymbols) {
            if (!getWord().toString().equals("") &&
                    symbol.equals(new Symbol('('))) {
                temp = symbol.toString();
            }
            else {
                stringBuilder.append(symbol.toString());
            }
        }
        return temp + word.toString() + stringBuilder.append(' ');
    }
}
