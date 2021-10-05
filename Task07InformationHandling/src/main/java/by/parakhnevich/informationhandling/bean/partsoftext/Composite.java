package by.parakhnevich.informationhandling.bean.partsoftext;

public interface Composite {
    public Composite getChild(int index);

    public void remove(int index);

    public void swap(int index1, int index2);

    public void add(Composite composite);

    public int getSize();
}
