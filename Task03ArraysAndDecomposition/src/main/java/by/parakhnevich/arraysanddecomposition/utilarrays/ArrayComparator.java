package by.parakhnevich.arraysanddecomposition.utilarrays;

import by.parakhnevich.arraysanddecomposition.bean.Array;

public class ArrayComparator {
    public void checkingBounds(int index , Array<Number> array) throws ArrayIndexOutOfBoundsException{
        if (index < 0 || index >= array.getLength()) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
