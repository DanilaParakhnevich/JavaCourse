package by.parakhnevich.arraysanddecomposition.utilarrays;

import by.parakhnevich.arraysanddecomposition.entity.Array;

public class Sorting {
    protected void swap(Array<Number> array , int index1, int index2) throws ArrayIndexOutOfBoundsException{
        array.put(index1, array.getDoubleValue(index1) +
                array.getDoubleValue(index2));
        array.put(index2, array.getDoubleValue(index1) -
                array.getDoubleValue(index2));
        array.put(index1, array.getDoubleValue(index1) -
                array.getDoubleValue(index2));
    }
}
