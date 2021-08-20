package by.parakhnevich.arraysanddecomposition.service.utilarrays;

import by.parakhnevich.arraysanddecomposition.bean.Array;

public class ArrayValidator {
    public boolean checkingBounds(int index , Array<Number> array) throws ArrayIndexOutOfBoundsException{
        return index >= 0 && index < array.getLength();
    }
}
