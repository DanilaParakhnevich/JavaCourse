package by.parakhnevich.arraysanddecomposition.utilarrays;

import by.parakhnevich.arraysanddecomposition.bean.Array;

/**
 * Merge sorting
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.utilarrays.Sorting
 */
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
                    CompareTwoNumbers.compare(secondPart.get(secondIndex),firstPart.get(firstIndex)))
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
