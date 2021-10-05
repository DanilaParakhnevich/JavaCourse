package by.parakhnevich.informationhandling.bean.partsoftext;

import java.util.Objects;

public class Symbol implements Composite{
    private char element;

    public Symbol(char element) {
        this.element = element;
    }

    @Override
    public Composite getChild(int index) {
        return null;
    }

    @Override
    public void remove(int index) {
        //we cannot remove child of symbol because it is leaf
    }

    @Override
    public void swap(int index1, int index2) {
        //we cannot swap children of symbol because it is leaf
    }

    @Override
    public void add(Composite composite) {
        //we cannot add composite to symbol because it is leaf
    }

    @Override
    public int getSize() {
        return 1;
    }

    public void setElement(char element) {
        this.element = element;
    }

    public char getElement() {
        return this.element;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol1 = (Symbol) o;
        return element == symbol1.element;
    }

    @Override
    public int hashCode() {
        return Objects.hash(element);
    }

    @Override
    public String toString() {
        return String.valueOf(element);
    }
}
