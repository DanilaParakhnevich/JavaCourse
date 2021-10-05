package by.parakhnevich.informationhandling.bean.partsoftext;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Paragraph implements Composite{
    private List<Composite> listOfSentences = new ArrayList<>();

    public void add(Sentence sentence) {
        listOfSentences.add(sentence);
    }

    public Paragraph(List<Composite> listOfSentences) {
        this.listOfSentences = new ArrayList<>(listOfSentences);
    }

    @Override
    public Composite getChild(int index) {
        return listOfSentences.get(index);
    }

    @Override
    public void remove(int index) {
        listOfSentences.remove(index);
    }

    @Override
    public void add(Composite composite) {
        listOfSentences.add(composite);
    }

    @Override
    public int getSize() {
        return listOfSentences.size();
    }

    public void setListOfSentences(List<Composite> listOfSentences) {
        this.listOfSentences = listOfSentences;
    }

    @Override
    public void swap(int index1, int index2) {
        Composite composite = listOfSentences.get(index1);
        listOfSentences.set(index1, listOfSentences.get(index2));
        listOfSentences.set(index2, composite);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paragraph paragraph = (Paragraph) o;
        return listOfSentences.equals(paragraph.listOfSentences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listOfSentences);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder().append('\t');
        listOfSentences.forEach(element -> result.append(element.toString()));
        return result.toString();
    }
}
