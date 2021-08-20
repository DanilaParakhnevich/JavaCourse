package by.parakhnevich.arraysanddecomposition.utilarrays;

import by.parakhnevich.arraysanddecomposition.bean.Array;

/**
 *Selection sorting
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.arraysanddecomposition.utilarrays.Sorting
 */
public class SelectionSorting extends Sorting{
    public void sort(Array<Number> array){
        for (int index1 = 0 ; index1 < array.getLength(); index1++){
            int minIndex = index1;
            for (int index2 = index1 + 1 ; index2 < array.getLength(); index2++){
                if (CompareTwoNumbers.compare(array.get(minIndex),array.get(index2))){
                    minIndex = index2;
                }
            }
            if (minIndex != index1){
                super.swap(array, minIndex, index1);
            }
        }
    }
}
