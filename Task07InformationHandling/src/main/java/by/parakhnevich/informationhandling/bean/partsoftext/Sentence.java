package by.parakhnevich.informationhandling.bean.partsoftext;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sentence implements Composite{
    private List<Composite> listOfLexemes;

    public Sentence(List<Composite> listOfLexemes) {
        this.listOfLexemes = new ArrayList<>(listOfLexemes);
    }

    @Override
    public Composite getChild(int index) {
        return listOfLexemes.get(index);
    }

    @Override
    public void swap(int index1, int index2) {
        Lexeme lexeme1 = (Lexeme) listOfLexemes.get(index1);
        Lexeme lexeme2 = (Lexeme) listOfLexemes.get(index2);
        Word word = lexeme1.getWord();
        lexeme1.setWord(lexeme2.getWord());
        lexeme2.setWord(word);
    }
    
    public void swap(int index1, int index2, boolean flag) {
        Composite composite1 = listOfLexemes.get(index1);
        Composite composite2 = listOfLexemes.get(index2);
        if (composite1.getSize() > 0 && composite2.getSize() > 0 &&
                composite1.getChild(composite1.getSize() - 1).equals(new Symbol('\n'))
        && !composite2.getChild(composite2.getSize() - 1).equals(new Symbol('\n'))) {
            composite1.remove(composite1.getSize()-1);
            composite2.add(new Symbol('\n'));
        }
        else if (composite1.getSize() > 0 && composite2.getSize() > 0 &&
                !composite1.getChild(composite1.getSize() - 1).equals(new Symbol('\n'))
                && composite2.getChild(composite2.getSize() - 1).equals(new Symbol('\n'))) {
            composite2.remove(composite2.getSize()-1);
            composite1.add(new Symbol('\n'));
        }
        listOfLexemes.set(index1, composite2);
        listOfLexemes.set(index2, composite1);
    }

    @Override
    public void remove(int index) {
        listOfLexemes.remove(index);
    }

    @Override
    public void add(Composite composite) {
        listOfLexemes.add(composite);
    }

    @Override
    public int getSize() {
        return listOfLexemes.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(listOfLexemes, sentence.listOfLexemes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listOfLexemes);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        listOfLexemes.forEach(element -> result.append(element.toString()));
        return result.toString();
    }
}
