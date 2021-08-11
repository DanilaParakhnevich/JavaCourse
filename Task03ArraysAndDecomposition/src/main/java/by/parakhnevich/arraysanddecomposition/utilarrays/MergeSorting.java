package by.parakhnevich.arraysanddecomposition.utilarrays;

import by.parakhnevich.arraysanddecomposition.entity.Array;

public class MergeSorting extends Sorting{
    public void sort(Array<Number> array) {
        int middle = array.getLength()/2;
        Array<Number> firstPart = new Array<>(0, middle, array);
        Array<Number> secondPart = new Array<>(middle, array.getLength(), array);
        new InsertionSorting().sort(firstPart);
        new InsertionSorting().sort(secondPart);
        int firstIndex = 0;
        int secondIndex = 0;
        for (int index = 0 ; index < array.getLength() ; index++){
            if (secondIndex >= secondPart.getLength() || firstIndex < firstPart.getLength() &&
                    firstPart.getDoubleValue(firstIndex) < secondPart.getDoubleValue(secondIndex))
            {
                array.put(index,firstPart.get(firstIndex));
                firstIndex++;
            }
            else {
                array.put(index,secondPart.get(secondIndex));
                secondIndex++;
            }
        }
    }

}
